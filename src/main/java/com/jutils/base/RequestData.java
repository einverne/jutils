package com.jutils.base;

import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class RequestData implements Serializable {
	public static final String CHAR_AND = "&";
	public static final String CHAR_EQUALS = "=";
	public static final String CHAR_QM = "?";
	public static final String CHAR_SET_UTF_8 = "UTF-8";
	public static final String DELETE = "DELETE";
	public static final String EMPTY = "";
	public static final String GET = "GET";
	public static final String PATCH = "PATCH";
	public static final String POST = "POST";
	public static final String PUT = "PUT";
	private String mContentType;
	private HashMap<String, Object> mHeaderData;
	private String mMethod = "GET";
	private HashMap<String, Object> mPostData;
	private HashMap<String, Object> mQueryData;
	private String mRequestBody;
	private String mTag;
	public String mUrl;

	public static String buildQueryString(Map<String, ?> map, String str) {
		return buildQueryString(map, str, true);
	}

	public static String buildQueryString(Map<String, ?> map, String str, boolean z) {
		if (map == null || map.size() == 0) {
			return str;
		}
		StringBuilder stringBuilder = new StringBuilder();
		Object obj = null;
		if (str != null) {
			stringBuilder.append(str);
			if (str.contains(CHAR_QM)) {
				obj = 1;
			} else {
				stringBuilder.append(CHAR_QM);
			}
		}
		Object obj2 = obj;
		for (Entry entry : map.entrySet()) {
			if (obj2 != null) {
				stringBuilder.append(CHAR_AND);
				Object obj3 = obj2;
			} else {
				int i = 1;
			}
			try {
				if (StringUtils.isEmpty((String) entry.getKey())) {
					obj2 = true;
				} else {
					stringBuilder.append(z ? URLEncoder.encode((String) entry.getKey(), "UTF-8") : (String) entry.getKey());
					stringBuilder.append("=");
					if (entry.getValue() != null) {
						stringBuilder.append(z ? URLEncoder.encode(entry.getValue().toString(), "UTF-8") : entry.getValue().toString());
					}
					obj2 = true;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return stringBuilder.toString();
	}

	public static Map<String, String> parseQuery(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		Map<String, String> linkedHashMap = new LinkedHashMap();
		for (String str2 : str.split(CHAR_AND)) {
			int indexOf = str2.indexOf("=");
			try {
				linkedHashMap.put(URLDecoder.decode(str2.substring(0, indexOf), "UTF-8"), URLDecoder.decode(str2.substring(indexOf + 1), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return linkedHashMap;
	}


}
