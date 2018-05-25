package com.jutils.instagram;

import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.Stack;

/**
 * Instagram ID 生成方式
 */
public class Instagram {
    public static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";

    public static final String ENCODE32 = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";  // 去掉 I 1 0 O

    public static String decimalToBinary(int num) {
//        Integer.toBinaryString(num);

        // using stack
        Stack<Byte> stack = new Stack<>();
        while (num != 0) {
            stack.push((byte) (num % 2));
            num = num / 2;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static int binaryToDecimal(String binary) {
//        Integer.parseInt(binary, 2);
        int ret = 0;
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                ret += Math.pow(2, binary.length() - 1 - i);
            }
        }
        return ret;
    }

    public static String numberId2UrlId(String numberId) {
        if (numberId.length() != 18) {
            return "";
        }
        BigInteger nId = new BigInteger(numberId, 10);
        String str64 = nId.toString(2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i + 6 <= str64.length(); i += 6) {
            String sub = str64.substring(i, i + 6);
            int index = Integer.parseInt(sub, 2);
            sb.append(alphabet.charAt(index));
        }
        return sb.toString();
    }

    /**
     * 10 位 UID
     *
     * @param uid
     * @return
     */
    public static String uid2Code(String uid) {
        if (StringUtils.isEmpty(uid)) return "";
        if (uid.length() != 10) return "";
        BigInteger nId = new BigInteger(uid, 10);
        String str32 = nId.toString(2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i + 5 < str32.length(); i += 5) {
            String sub = str32.substring(i, i + 5);
            int index = Integer.parseInt(sub, 2);
            sb.append(ENCODE32.charAt(index));
        }
        return sb.toString();
    }

    public static String UrlId2NumberId(String urlId) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < urlId.length(); i++) {
            int index = alphabet.indexOf(urlId.charAt(i));
            String bi = String.format("%6s", Integer.toBinaryString(index)).replace(' ', '0');
            sb.append(bi);
        }
        String binaryStr = sb.toString();
        BigInteger ret = new BigInteger(binaryStr, 2);
        return ret.toString();
    }

    public static String code2Uid(String encode) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encode.length(); i++) {
            int index = ENCODE32.indexOf(encode.charAt(i));
            String bi = String.format("%5s", Integer.toBinaryString(index)).replace(' ','0');
            sb.append(bi);
        }
        String binaryStr = sb.toString();
        BigInteger ret = new BigInteger(binaryStr, 2);
        return ret.toString();
    }

    public static void main(String[] args) {
//        String s = numberId2UrlId("908540701891980503");
//        System.out.println(s);
//        String s1 = UrlId2NumberId(s);
//        System.out.println(s1);
//
//        Assert.assertTrue(decimalToBinary(999).equals("1111100111"));
//        System.out.println(decimalToBinary(9));
//        Assert.assertTrue(binaryToDecimal("111111") == Integer.parseInt("111111", 2));

        String s = uid2Code("1234567890");
        System.out.println(s);

        String unyamk = code2Uid("UNYAMK");
        System.out.println(unyamk);
    }

}
