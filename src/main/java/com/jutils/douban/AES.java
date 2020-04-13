package com.jutils.douban;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	public static final String sign(String str, String str2) {
		try {
			SecretKeySpec keySpec = getKeySpec(str2);
			byte[] decode = Base64.decode(str, 0);
			Cipher instance = Cipher.getInstance("AES/CBC/NoPadding");
			instance.init(2, keySpec, new IvParameterSpec("DOUBANFRODOAPPIV".getBytes()));
			return new String(instance.doFinal(decode));
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return str;
	}

	private static SecretKeySpec getKeySpec(String str) {
		if (str == null) {
			str = "";
		}
		StringBuilder stringBuilder = new StringBuilder(16);
		stringBuilder.append(str);
		while (stringBuilder.length() < 16) {
			stringBuilder.append("\u0000");
		}
		if (stringBuilder.length() > 16) {
			stringBuilder.setLength(16);
		}
		byte[] str2 = null;
		try {
			str2 = stringBuilder.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new SecretKeySpec(str2, "AES");
	}

	public static void main(String[] args) {
		String sign = sign("abc", "abc");
		System.out.println(sign);
	}
}
