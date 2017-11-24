package com.jutils.date;

import org.junit.Test;

import java.util.Date;

/**
 * Created by mi on 17-5-15.
 */
public class DateUtilsTest {
    @Test
    public void parseDate() throws Exception {
        Date date = DateUtils.parseDate("Oct 1, 2015 11:00:00 AM", "MMM dd, yyyy hh:mm:ss a");
        System.out.println(date);
    }

    @Test
    public void getCurrentTime2() throws Exception {

    }

    @Test
    public void getCurrentTime3() throws Exception {

    }

    @Test
    public void getCurrentDate2() throws Exception {

    }

    @Test
    public void getCurrentDate3() throws Exception {

    }

    @Test
    public void addYearToDate() throws Exception {

    }

    @Test
    public void addYearToDate1() throws Exception {

    }

    @Test
    public void addMothToDate() throws Exception {

    }

    @Test
    public void addMothToDate1() throws Exception {

    }

    @Test
    public void addDayToDate() throws Exception {

    }

    @Test
    public void addDayToDate1() throws Exception {

    }

    @Test
    public void addHourToDate() throws Exception {

    }

    @Test
    public void addHourToDate1() throws Exception {

    }

    @Test
    public void addMinuteToDate() throws Exception {

    }

    @Test
    public void addMinuteToDate1() throws Exception {

    }

    @Test
    public void addSecondToDate() throws Exception {

    }

    @Test
    public void addSecondToDate1() throws Exception {

    }

    @Test
    public void getCalendar() throws Exception {

    }

    @Test
    public void string2Date() throws Exception {
        Date date = DateUtils.string2Date("2017-11-11 19:12:01");
        System.out.println(date);
    }

    @Test
    public void string2Date1() throws Exception {

    }

    @Test
    public void date2String() throws Exception {

    }

    @Test
    public void date2String1() throws Exception {

    }

    @Test
    public void getCurrentYear() throws Exception {

    }

    @Test
    public void getCurrentYear1() throws Exception {

    }

    @Test
    public void getCurrentMonth() throws Exception {

    }

    @Test
    public void getCurrentMonth1() throws Exception {

    }

    @Test
    public void getCurrentDay() throws Exception {
        int currentDay = DateUtils.getCurrentDay("2017-11-15");
        System.out.println(currentDay);
    }

    @Test
    public void getCurrentDay1() throws Exception {
        int currentDay = DateUtils.getCurrentDay(new Date());
        System.out.println(currentDay);
    }

    @Test
    public void getCurrentWeek() throws Exception {
        String currentWeek = DateUtils.getCurrentWeek(new Date());
        System.out.println(currentWeek);
    }

    @Test
    public void getCurrentWeek1() throws Exception {
        String currentWeek = DateUtils.getCurrentWeek("2018-01-01");
        System.out.println(currentWeek);
    }

    @Test
    public void getCurrentHour() throws Exception {
        int currentHour = DateUtils.getCurrentHour(new Date());
        System.out.println(currentHour);    // 24h 制
    }

    @Test
    public void getCurrentHour1() throws Exception {
        int currentHour = DateUtils.getCurrentHour("2017-11-11 01:23:59");
        System.out.println(currentHour);    // 24h 制
    }

    @Test
    public void getCurrentMinute() throws Exception {

    }

    @Test
    public void getCurrentMinute1() throws Exception {

    }

    @Test
    public void compareDate() throws Exception {

    }

    @Test
    public void compareTime() throws Exception {

    }

    @Test
    public void compare() throws Exception {

    }

    @Test
    public void getMonthFirstDate() throws Exception {

    }

    @Test
    public void getMonthLastDate() throws Exception {

    }

    @Test
    public void getWeekFirstDate() throws Exception {

    }

    @Test
    public void geWeektLastDate() throws Exception {

    }

    @Test
    public void getCurrentTime() throws Exception {
        String currentTime = DateUtils.getCurrentTime();
        System.out.println(currentTime);
    }

    @Test
    public void getCurrentTime1() throws Exception {
        String currentTime = DateUtils.getCurrentTimeWithFormat(DateFormatUtils.TIME_NOFUll_FORMAT);
        System.out.println(currentTime);
    }

    @Test
    public void getCurrentDate() throws Exception {
        Date currentDate = DateUtils.getCurrentDate();
        System.out.println(currentDate);
    }

    @Test
    public void getCurrentDate1() throws Exception {
        Date currentDate = DateUtils.getCurrentDateWithFormat(DateFormatUtils.DATE_FORMAT1);
        System.out.println(currentDate);
    }

}