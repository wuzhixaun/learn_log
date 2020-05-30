package com.study.learn_log.annotation;

import java.lang.reflect.Method;
import java.util.Arrays;

public interface Enums extends EnumDisplay{

	public int getCode();
	

    static <T extends Enums> T valueOf(int code, Class<T> enumType) {
        try {
            Method method = enumType.getMethod("values");
            T[] ts = (T[]) method.invoke(enumType);
            System.out.println(Arrays.toString(ts));
            for (T t : ts) {
                if (t.getCode() == code) {
                    return t;
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return null;
    }
    
    static <T extends Enums> boolean isValid(int code, Class<T> enumType) {
        T result = Enums.valueOf(code, enumType);
        if (result == null) {
            return false;
        }

        return true;
    }
    
}
