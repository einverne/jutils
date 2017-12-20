package com.jutils.re;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mi on 17-11-23.
 */
public class ReUtil {

    /**
     * return the first group
     *
     * @param regex regex pattern
     * @param text text
     * @return
     */
    public static String m1(String regex, String text) {
        Pattern compile = Pattern.compile(regex);
        return m1(compile, text);
    }

    public static String m1(Pattern pattern, String text) {
        Matcher m = pattern.matcher(text);
        if (m.find()) {
            return m.group();
        }
        return "";
    }

    public static boolean isMatch(Pattern pattern, String text) {
        Matcher m = pattern.matcher(text);
        return m.find();
    }

    public static boolean isMatch(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        return isMatch(pattern, text);
    }

}
