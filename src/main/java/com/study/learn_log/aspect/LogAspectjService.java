package com.study.learn_log.aspect;

import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;
import com.study.learn_log.annotation.Log;
import com.study.learn_log.utils.JSON2Helper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LogAspectjService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspectjService.class);

    /**
     * 来截取所有被打上@Log 标签的对象。
     *
     * execution()：
     * execution( modifier-pattern?修饰符
     * ret-type-pattern返回类型
     * declaring-type-pattern?方法模式
     * name-pattern(param-pattern)
     * throws-pattern
     * )
     * @pointcut("execution(* com.sample.service.impl..*.*(..))")
     */
    @Pointcut("@annotation(com.study.learn_log.annotation.Log)")
    private void controller() {

    }

    /**
     * 表示对所截取的对象方法在执行前插入这块新的代码，并原方法进行修改替换
     */
    @Before("controller()")
    public void log(JoinPoint joinPoint) {
        LOGGER.info("LogAspectjService log joinPoint {}", joinPoint);
        // 获取连接点的参数签名对象---signature =String com.study.learn_log.LogControoller.testLog(String,HttpServletRequest,HttpServletResponse)
        Signature signature = joinPoint.getSignature();
        LOGGER.info("------------signature ={} ", signature);


        MethodSignature methodSignature = (MethodSignature) signature;
        // 获取该对象的方法
        Method targetMethod = methodSignature.getMethod();

        // 获取方法的 类型为Log 注解
        Log log = targetMethod.getAnnotation(Log.class);
        if (log == null) {
            return;
        }

        // 获取 连接点方法运行时的入参列表，参数值
        Object[] args = joinPoint.getArgs();
        LOGGER.info("------------args ={} ", args);
        // 目标方法的参数 参数类型+ 参数名 java.lang.String name
        Parameter[] parameters = targetMethod.getParameters();
        LOGGER.info("------------parameters ={} ", parameters);

        Map data = new HashMap<>();
        for (int index = 0; index < parameters.length; index++) {
            Object value = args[index];
            LOGGER.info("------------value ={} ", value.getClass());

            // 参数如果是 req或rsp
            if (value instanceof HttpServletRequest || value instanceof HttpServletResponse) {
                continue;
            }

            Object valueObject = value;

            if(valueObject instanceof RequestEntity) {
                valueObject = ((RequestEntity) valueObject).getBody();
            }

            // 参数如果是protobuf 则进行相应转换
            if (valueObject instanceof Message) {
                String json = new JsonFormat().printToString((Message) valueObject);
                valueObject = JSON2Helper.toObject(json, Map.class);
            }

            LOGGER.info("entry key {} ,value {}", parameters[index].getName(), valueObject);
            data.put(parameters[index].getName(), valueObject);
        }


        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        // 这里可以一直往request设置值吗？
        request.setAttribute("logData", data);
    }
}
