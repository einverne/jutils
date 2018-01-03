package com.jutils.re;

import org.junit.Test;

import java.util.regex.Pattern;

/**
 * Created by mi on 17-11-23.
 */
public class ReUtilTest {
    @Test
    public void m1() throws Exception {
        Pattern pattern = Pattern.compile("(\\d+)");
        String s = ReUtil.m1(pattern, "This is a 9876 test");
        System.out.println(s);
    }

    @Test
    public void m11() throws Exception {

    }
}