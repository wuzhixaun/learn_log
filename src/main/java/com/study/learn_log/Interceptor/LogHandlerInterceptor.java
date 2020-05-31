package com.study.learn_log.Interceptor;

import com.study.learn_log.annotation.Log;
import com.study.learn_log.exception.BizException;
import com.study.learn_log.model.LogContent;
import com.study.learn_log.model.LogInfoForSign;
import com.study.learn_log.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class LogHandlerInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LogHandlerInterceptor.class);

    private int ERROR_MSG_MAX_LENGTH = 1024;

    private int EXE_TIME_MAX = 200;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("time" ,System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;

            Log log = method.getMethodAnnotation(Log.class);
            if (log == null) {
                return;
            }
            long currentTime = System.currentTimeMillis();

            // 全局异常捕获的时候设置
            Throwable throwable = (Throwable) request.getAttribute("logException");
            Map<String, Object> data = (Map) request.getAttribute("logData");
            if (throwable != null) {

                if(throwable instanceof BizException) {
                    BizException biz = (BizException)throwable;
                    data.put("errorCode", biz.getCode());
                    data.put("errorMsg", biz.getMsg());

                }else {
                    String errorMsg = ExceptionUtils.getErrorMsg(throwable, ERROR_MSG_MAX_LENGTH);

                    //MSG_UNKNOWN_ERR(10000000,   "服务器错误, 请稍候重试!");
                    data.put("errorCode", "10000000");
                    data.put("errorMsg", "服务器错误, 请稍候重试!");
                }
            }


            String userAgent = request.getHeader("User-Agent");
            data.put("userAgent", userAgent);

            //记录版本号
            data.put("X-B-Version", request.getHeader("X-C-Version"));

            // 获取 deviceInfo
            request.getAttribute("userAgent");

            // preHandler设置
            long time = (long) request.getAttribute("time");

            String userId = (String) request.getAttribute("userId");
            String ip = request.getHeader("X-Forwarded-For");
            if (ip == null) {
                ip = request.getRemoteAddr();
            }
            if (ip != null) {
                String[] ips = ip.split(",");
                ip = ips[0];
            }

            // 获取log类型

            int logType = log.value().getLogType();


            // 方法执行时间
            long exeTime = currentTime - time;
            data.put("exeTime", exeTime);

            /*
             *  Object responseObj = getResponseData(response, logType);
             *  将 protobuf 以及json hashmap设置content-type
             */
            Object responseObj = null;
            data.put("response", responseObj);

            LogContent content = new LogContent();
            content.setData(data);

            LogInfoForSign logInfo = new LogInfoForSign();
            logInfo.setTime((int)(time/1000));
            logInfo.setTopic(3);
            logInfo.setSource("1");
            logInfo.setCity("0");
            logInfo.setClientIp(ip);
            logInfo.setLogType(log.value().getLogType());


            // 设置子logtype
            logInfo.setSubLogType(1);

            if(throwable == null && exeTime > EXE_TIME_MAX) {
                // 超时
//                Integer subLogType = log.value().getErrorSubLogType().apply(new ExeTimeTooLongException());
//                logInfo.setSubLogType(subLogType);
            }

            // 进行日志上报

            logger.info("========data {} ", data);
        }
    }



}
