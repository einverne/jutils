package com.jutils.joda;

import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Date;

/**
 * test joda lib
 *
 * http://www.joda.org/joda-time/
 */
public class JodaTest {

    @Test
    public void jodatest() {
        Date date = new Date();
        DateTime dateTime = new DateTime(date);
        int monthOfYear = dateTime.getMonthOfYear();
        int year = dateTime.getYear();

        // DateTime classes immutable
        // 2018 年 1 月 2 号 2:20:30
        DateTime dateTime1 = new DateTime().withYear(2018).withMonthOfYear(1).withDayOfMonth(2).withHourOfDay(2).withMinuteOfHour(20).withSecondOfMinute(30);
    }
}
