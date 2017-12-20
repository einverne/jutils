package com.jutils.base;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by mi on 17-12-20.
 */
public class IdCardUtilsTest {
    @Test
    public void isValidate() throws Exception {
        Assert.assertFalse(IdCardUtils.isValidate("110231198804091256"));
        Assert.assertTrue(IdCardUtils.isValidate("11023119880409125X"));
    }

}