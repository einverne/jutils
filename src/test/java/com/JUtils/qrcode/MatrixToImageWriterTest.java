package com.JUtils.qrcode;

import com.google.zxing.common.BitMatrix;
import org.junit.Test;

import java.io.File;

/**
 * 二维码
 */
public class MatrixToImageWriterTest {
    @Test
    public void writeToFile() throws Exception {
        BitMatrix matrix = MatrixToImageWriterEx.createQRCode("hello", 250, 250);
        File file = new File("/tmp/qrcode.png");
        MatrixToImageWriter.writeToFile(matrix, "png", file);
    }

    @Test
    public void writeToFileCustomColor() throws Exception {
        BitMatrix matrix = MatrixToImageWriterEx.createQRCode("hello", 250, 250);
        File file = new File("/tmp/qrcode_custom.png");
        MatrixToImageConfig config = new MatrixToImageConfig(0xFFFF0000, 0xFF00FF00);
        MatrixToImageWriter.writeToFile(matrix, "png", file, config);
    }

}