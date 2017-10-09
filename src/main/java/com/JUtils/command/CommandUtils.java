package com.JUtils.command;

import java.io.File;

/**
 * Created by mi on 17-7-31.
 */
public class CommandUtils {

    public static void runCommand(String videoPath) {
        try {
            Runtime rt = Runtime.getRuntime();
            String filePath = "/tmp/test.jpg";
            String command = "/usr/bin/ffmpeg -ss 1 -i " + videoPath + " -f image2 -vframes 1 " + filePath;
            Process process = rt.exec(command);
            if (process.waitFor() == 0) {
                File thumbFile = new File(filePath);
                boolean deleteResult = thumbFile.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
