package com.study.learn_log.constant;

import com.study.learn_log.exception.BizException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class Constants {


    public static enum LogType{

        TEST(1, 2000, ERROR_SUB_LOG_TYPE, "测试");


        private int logType;
        private int subLogType;
        private Function<Throwable, Integer> errorSubLogType;
        private String desc;
        private LogType(int logType, int subLogType, Function<Throwable, Integer> errorSubLogType, String desc) {
            this.logType = logType;
            this.subLogType = subLogType;
            this.errorSubLogType = errorSubLogType;
            this.desc = desc;
        }
        public int getLogType() {
            return logType;
        }
        public int getSubLogType() {
            return subLogType;
        }
        public Function<Throwable, Integer> getErrorSubLogType() {
            return errorSubLogType;
        }
        public String getDesc() {
            return desc;
        }
    }

    private static final Map<Class, ErrorSubLogType> ERROR_SUB_LOG_TYPES = new HashMap<>();
    public static enum ErrorSubLogType {
        BIZ_EXCEPTION(100, BizException.class, "业务异常"),
        SYSTEM_EXCEPTION(101, Exception.class, "系统异常"),
        SYSTEM_EXCEPTION2(101, Throwable.class, "系统异常"),
        UNKNOW_EXCEPTION(102, null, "未知异常"),
        ;

        private int code;
        private Class clazz;
        private String desc;
        private ErrorSubLogType(int code, Class clazz, String desc) {
            ERROR_SUB_LOG_TYPES.put(clazz, this);
            this.code = code;
            this.clazz = clazz;
            this.desc = desc;
        }
        public int getCode() {
            return code;
        }
        public String getDesc() {
            return desc;
        }
        public Class getClazz() {
            return clazz;
        }

        public static ErrorSubLogType get(Class clazz) {
            return ERROR_SUB_LOG_TYPES.get(clazz);
        }
    }

    private static final Function<Throwable, Integer> ERROR_SUB_LOG_TYPE = throwable -> {
        ErrorSubLogType errorType = ErrorSubLogType.get(throwable.getClass());
        return Optional.ofNullable(errorType)
                .map(errorSubLogTyp->errorSubLogTyp.getCode())
                .orElse(ErrorSubLogType.UNKNOW_EXCEPTION.getCode());
    };
}
