package com.jutils.re;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则相关方法
 */
public class ReUtil {

    /**
     * return the first group
     *
     * @param regex regex pattern
     * @param text text
     * @return first match
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

    public static String rmPunctuation(String title) {
        return title.replaceAll("[\\pP\\pZ\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\`\\-\\=\\[\\]\\;\\'\\,\\.\\/{}|:\"<>\\?]+", "");
    }
}
