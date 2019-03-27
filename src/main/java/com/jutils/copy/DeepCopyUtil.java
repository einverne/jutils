package com.jutils.copy;

import com.google.gson.Gson;

/**
 * @author einverne
 * @date 2019-03-01
 */
public class DeepCopyUtil {

	public static <T> T copy(T obj, Class<T> cls) {
		try {
			return copyImpl(obj, cls);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	private static <T> T copyImpl(T obj, Class<T> cls) {
		Gson gson = new Gson();
		return gson.fromJson(gson.toJson(obj), cls);
	}
}
