package com.JUtils.common;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

/**
 * Created by mi on 17-10-9.
 */
public class ApacheCommonsTest {
    @Test
    public void binaryEncodersTest() {
        String encoded = Base64.encodeBase64String("hello".getBytes());
        System.out.println(encoded);
    }

    @Test
    public void binaryDecodeTest() {
        String encoded = "aGVsbG8=";
        byte[] bytes = Base64.decodeBase64(encoded);
        System.out.println(new String(bytes));
    }

    @Test
    public void urlCodecTest() {
        URLCodec codec = new URLCodec();
        String toEncode = "abc%def&ghi";
        try {
            String encoded = codec.encode(toEncode);
            System.out.println("Encoded "+ encoded);
            System.out.println("Decoded "+ codec.decode(encoded));
        } catch (EncoderException | DecoderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void commonsLang() {
        int[] arr = new int[10];
        ArrayUtils.add(arr, 1);
    }

}
