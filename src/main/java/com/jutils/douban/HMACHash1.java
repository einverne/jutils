package com.jutils.douban;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMACHash1 {
	public static final String HMAC_SHA1 = "HmacSHA1";
	public static final String encode(String s1, String s2) {
		try {
			Key secretKeySpec = new SecretKeySpec(s1.getBytes(), HMAC_SHA1);
			Mac instance = Mac.getInstance(HMAC_SHA1);
			instance.init(secretKeySpec);
			byte[] doFinal = instance.doFinal(s2.getBytes());
			return Base64.encodeToString(doFinal, 2);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {

	}
}
