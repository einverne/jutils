package com.jutils.captcha;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

/**
 * Created by einverne on 5/13/17.
 */
public class CaptchaUtilsTest {
    @Test
    public void createToFile() throws Exception {
        CaptchaUtils.createToFile("/tmp/captcha.jpg", "abcd");
    }

    @Test
    public void createToByte() throws Exception {
        byte[] img = CaptchaUtils.createToByte("abcd");
        String imaStr = new String(Base64.encodeBase64(img));
    }

}