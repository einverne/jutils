package com.jutils.date;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by mi on 17-5-15.
 */
public class TimestampUtilsTest {
    @Test
    public void string2Timestamp() throws Exception {

    }

    @Test
    public void timestamp2String() throws Exception {

    }

    @Test
    public void date2Timestamp() throws Exception {
        Timestamp timestamp = TimestampUtils.date2Timestamp(DateUtils.getCurrentDate());
        System.out.println(timestamp.getTime());
    }

    @Test
    public void timestamp2Date() throws Exception {
        Date date = TimestampUtils.timestamp2Date(new Timestamp(1494827287000L));
        System.out.println(date);
    }

}