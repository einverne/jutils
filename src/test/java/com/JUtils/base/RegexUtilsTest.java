package com.JUtils.base;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

public class RegexUtilsTest {
    @Test
    public void isEmail() throws Exception {
        Assert.assertTrue(RegexUtils.isEmail("test@gmail.com"));
        Assert.assertTrue(RegexUtils.isEmail("test.1@gmail.com"));
        Assert.assertTrue(RegexUtils.isEmail("test-1@gmail.com"));
        Assert.assertTrue(RegexUtils.isEmail("test+name@gmail.com"));
        Assert.assertTrue(RegexUtils.isEmail("test+name@foxmail.com"));
        Assert.assertTrue(RegexUtils.isEmail("test+name@sina.com.cn"));
        Assert.assertFalse(RegexUtils.isEmail("test@@@@.com"));
    }

    @Test
    public void isChinese() throws Exception {

    }

    @Test
    public void isDouble() throws Exception {

    }

    @Test
    public void isInteger() throws Exception {

    }

    @Test
    public void isPhone() throws Exception {
        Assert.assertTrue(RegexUtils.isPhone("13112345678"));
    }

}