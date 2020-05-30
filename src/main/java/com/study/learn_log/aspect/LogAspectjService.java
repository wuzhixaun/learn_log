package com.study.learn_log.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LogAspectjService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspectjService.class);

    /**
     * 来截取所有被打上@Log 标签的对象。
     */
    @Pointcut("@annotation(com.study.learn_log.annotation.Log)")
    private void controller() {

    }

    /**
     * 表示对所截取的对象方法在执行前插入这块新的代码，并原方法进行修改替换
     */
    @Before("controller()")
    public void log(JoinPoint joinPoint) {

    }
}
