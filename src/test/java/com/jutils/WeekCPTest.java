package com.jutils;

import com.jutils.base.RequestData;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WeekCPTest {
	private static String sha1(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest instance = MessageDigest.getInstance("SHA-1");
		byte[] bytes = str.getBytes("UTF-8");
		instance.update(bytes, 0, bytes.length);
		return O000000o(instance.digest());
	}

	private static String O000000o(byte[] bArr) {
		StringBuilder stringBuilder = new StringBuilder();
		for (byte b : bArr) {
			int i = (b >>> 4) & 15;
			int i2 = 0;
			while (true) {
				char c = (i < 0 || i > 9) ? (char) ((i - 10) + 97) : (char) (i + 48);
				stringBuilder.append(c);
				int i3 = b & 15;
				i = i2 + 1;
				if (i2 >= 1) {
					break;
				}
				i2 = i;
				i = i3;
			}
		}
		return stringBuilder.toString();
	}

	public static String makeSign(Map<String, Object> map, Map<String, Object> map2, String str) {
		Map treeMap = new TreeMap();
		if (!(map == null || map.isEmpty())) {
			treeMap.putAll(map);
		}
		if (!(map2 == null || map2.isEmpty())) {
			treeMap.putAll(map2);
		}
		treeMap.remove("token");
		treeMap.remove("sign");
		try {
			return sha1(RequestData.buildQueryString(treeMap, null, false) + str);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}



	public static void main(String[] args) {
		HashMap<String, Object> query = new HashMap<>();
		query.put("d", "a");
		query.put("user_id", "5624198");
		query.put("timestamp", "1533102328");
		query.put("sm_device_id", "201807021511598922958a5fbc8a5de09cb9019d34a48b01c51f66d9435c31");
//		query.put("Token", "1576511275532288_5624198_1559013109_58f16d8bd82677acc30d87542f5504b0");

		query.put("start", "80");
		query.put("last_object_id", "1742");
		query.put("keyword", "");
		query.put("num", "20");
		String s = makeSign(query, new HashMap<>(), "025d25f5a69eb2818b6811ff6edb51b4");
		System.out.println(s);
	}
}
