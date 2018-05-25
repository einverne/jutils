package com.jutils.re;

import org.junit.Test;

import java.util.regex.Pattern;

public class ReUtilTest {
    @Test
    public void m1() throws Exception {
        Pattern pattern = Pattern.compile("(\\d+)");
        String s = ReUtil.m1(pattern, "This is a 9876 test");
        System.out.println(s);
    }

    @Test
    public void rm() {
        String s = ReUtil.rmPunctuation("这 就是街舞");
        System.out.println(s);
        s = ReUtil.rmPunctuation("《这! 就是街舞》《How Long》舞蹈混剪");
        System.out.println(s);
        s = ReUtil.rmPunctuation("长发 女神 田馥甄 翻唱《演员》，薛之谦听了也会惊叹！");
        System.out.println(s);
        boolean b = s.contains("这就是街舞");
        System.out.println(b);
    }
}