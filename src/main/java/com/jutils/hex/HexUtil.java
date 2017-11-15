package com.jutils.hex;

/**
 * Created by mi on 17-11-13.
 */
public class HexUtil {
    public static String str2Hex(String inputStr) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder();
        byte[] bytes = inputStr.getBytes();
        int bit;
        for (int i = 0; i < bytes.length; i++) {
            bit = (bytes[i] & 0x0f0) >> 4;
        }
        return "";
    }
}
