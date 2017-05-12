package com.JUtils.QRCode;

import com.google.zxing.common.BitMatrix;
import org.junit.Test;

import java.awt.*;

/**
 * 带Logo的二维码
 */
public class MatrixToImageWriterExTest {
    @Test
    public void writeToFile() throws Exception {
        BitMatrix matrix = MatrixToImageWriterEx.createQRCode("hello", 1000, 1000);
        MatrixToImageWriterEx.writeToFile(matrix, "png", "/tmp/qrcode.png", "/tmp/logo.png");
    }

    @Test
    public void writeToFile1() throws Exception {
        BitMatrix matrix = MatrixToImageWriterEx.createQRCode("hello", 1000, 1000);
        MatrixToLogoImageConfig config = new MatrixToLogoImageConfig(Color.BLUE, 4);
        MatrixToImageWriterEx.writeToFile(matrix, "png", "/tmp/qrcode.png", "/tmp/logo.png", config);
    }

}