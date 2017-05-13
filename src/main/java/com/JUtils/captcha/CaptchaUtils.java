package com.JUtils.captcha;

import com.github.cage.Cage;
import com.github.cage.GCage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 验证码生成
 */
public class CaptchaUtils {

    /**
     * 生成到文件
     *
     * @param filename 文件名
     * @param text 验证码内容
     * @throws IOException
     */
    public static void createToFile(String filename, String text) throws IOException {
        Cage cage = new GCage();

        OutputStream os = new FileOutputStream(filename, false);
        try {
            cage.draw(text != null ? text : cage.getTokenGenerator().next(), os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            os.close();
        }
    }

    public static byte[] createToByte(String text) {
        Cage cage = new GCage();
        byte[] bytes;
        bytes = cage.draw(text != null ? text : cage.getTokenGenerator().next());
        return bytes;
    }
}
