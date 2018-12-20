package com.jutils.base;

import org.junit.Assert;
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
        Assert.assertTrue(RegexUtils.isInteger("0"));
        Assert.assertTrue(RegexUtils.isInteger("1"));
        Assert.assertTrue(RegexUtils.isInteger("-1"));
        Assert.assertTrue(RegexUtils.isInteger("4300"));
        Assert.assertTrue(RegexUtils.isInteger("+4300"));
        Assert.assertFalse(RegexUtils.isInteger("02"));
        Assert.assertFalse(RegexUtils.isInteger("0200"));
        Assert.assertFalse(RegexUtils.isInteger("+0200"));
        Assert.assertFalse(RegexUtils.isInteger("0.2"));
        Assert.assertFalse(RegexUtils.isInteger("-0.2"));
        Assert.assertFalse(RegexUtils.isInteger("2/3"));
    }

    @Test
    public void isPhone() throws Exception {
        Assert.assertTrue(RegexUtils.isPhone("13112345678"));
    }

    @Test
    public void find() {
    }

    @Test
    public void isPhone1() {
    }

    @Test
    public void isEmail1() {
    }

    @Test
    public void isIdCard() {
    }

    @Test
    public void isChinese1() {
    }

    @Test
    public void isDouble1() {
    }

    @Test
    public void checkBirthday() {
    }

    @Test
    public void checkURL() {
    }

    @Test
    public void isInteger1() {
    }

    @Test
    public void isNegativeInteger() {
        Assert.assertTrue(RegexUtils.isNegativeInteger("-1"));
        Assert.assertFalse(RegexUtils.isNegativeInteger("0"));
        Assert.assertFalse(RegexUtils.isNegativeInteger("1"));
        Assert.assertFalse(RegexUtils.isNegativeInteger("1.2"));
    }

    @Test
    public void checkPostcode() {
    }

    @Test
    public void checkIpAddress() {
    }
}