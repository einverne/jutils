package com.jutils.json;

import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.base.Strings;
import java.io.InputStream;
import java.util.List;

public class JSONUtils {

	public static ObjectMapper objectMapper = new ObjectMapper();

	static {
		objectMapper.configure(Feature.IGNORE_UNKNOWN, true);
	}

	public static <T> T readValue(String content, Class<T> valueType) {
		try {
			return !Strings.isNullOrEmpty(content) ? objectMapper.readValue(content, valueType) : null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> List<T> readListValue(String content, Class<T> valueType) {
		try {
			return !Strings.isNullOrEmpty(content) ? (List) objectMapper.readValue(content,
					TypeFactory.defaultInstance().constructCollectionType(List.class, valueType)) : null;
		} catch (Exception var3) {
			throw new RuntimeException(var3);
		}
	}

	public static <T> T readValue(InputStream ins, Class<T> valueType) {
		try {
			return ins != null ? objectMapper.readValue(ins, valueType) : null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T readValue(String content, TypeReference<T> valueType) {
		try {
			return !Strings.isNullOrEmpty(content) ? objectMapper.readValue(content, valueType) : null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String writeValueAsString(Object value) {
		try {
			return value != null ? objectMapper.writeValueAsString(value) : null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] writeValueAsBytes(Object value) {
		try {
			return value != null ? objectMapper.writeValueAsBytes(value) : null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
