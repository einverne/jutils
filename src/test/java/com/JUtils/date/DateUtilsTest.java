package com.JUtils.date;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Date;

/**
 * Created by mi on 17-5-15.
 */
public class DateUtilsTest {
    @Test
    public void getCurrentTime() throws Exception {
        String currentTime = DateUtils.getCurrentTime();
        System.out.println(currentTime);
    }

    @Test
    public void getCurrentTime1() throws Exception {
        String currentTime = DateUtils.getCurrentTime(DateFormatUtils.TIME_NOFUll_FORMAT);
        System.out.println(currentTime);
    }

    @Test
    public void getCurrentDate() throws Exception {
        Date currentDate = DateUtils.getCurrentDate();
        System.out.println(currentDate);
    }

    @Test
    public void getCurrentDate1() throws Exception {
        Date currentDate = DateUtils.getCurrentDate(DateFormatUtils.DATE_FORMAT1);
        System.out.println(currentDate);
    }

}