package com.study.learn_log.annotation;


import com.study.learn_log.constant.Constants.LogType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 用于描述方法
@Retention(RetentionPolicy.RUNTIME) // 注解生命周期
public @interface Log {

    LogType value();
}
