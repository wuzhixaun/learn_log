package com.study.learn_log.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.joda.time.DateTimeZone;

import java.util.Date;

public class JSON2Helper {
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	static {
		OBJECT_MAPPER.registerModule(new JodaModule());
		OBJECT_MAPPER.setTimeZone(DateTimeZone.getDefault().toTimeZone());
		OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}

	/**
	 * 将Object对象转为JSON字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		String json = null;
		try {
			json = OBJECT_MAPPER.writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException("To json error, object is " + object + ";exception:" + e);
		}
		return json;
	}

	/**
	 * 将一个JSON字符串转换为Object对象
	 * 
	 * @param <T>
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T toObject(String json, Class<T> clazz) {
		T o = null;
		if (json != null) {
			try {
				o = OBJECT_MAPPER.readValue(json, clazz);
			} catch (Exception e) {
				throw new RuntimeException("Json string To object error, json is " + json + ";exception:" + e);
			}
		}
		return o;
	}



	
	public static void main(String[] args) {
		System.out.println(JSON2Helper.toJson(new java.sql.Date(System.currentTimeMillis())));
		System.out.println(JSON2Helper.toJson(new Date()));
	}

}