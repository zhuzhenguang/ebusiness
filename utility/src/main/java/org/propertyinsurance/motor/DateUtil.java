package org.propertyinsurance.motor;

import ins.framework.common.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: zhenguang.zhu
 * Date: 12-8-27
 * Time: 下午4:12
 */
public final class DateUtil {
    /*
     * 日期格式
     */
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获得T+2日期
     *
     * @return
     */
    public static String getTodayPlus2DateString() {
        return sdf.format(new DateTime(new Date()).addDay(2));
    }

    /**
     * 获得当前日期1年后的日期
     *
     * @return
     */
    public static String getTodayPlus1YearString() {
        return sdf.format(new DateTime(new Date()).addYear(1).addDay(-1));
    }

    /**
     * 获得T+2日期
     *
     * @return
     */
    public static Date getTodayPlus2Date() {
        return new DateTime(new Date()).addDay(2);
    }

    /**
     * 获得当前日期1年后的日期
     *
     * @return
     */
    public static Date getTodayPlus1Year() {
        return new DateTime(new Date()).addYear(1).addDay(-1);
    }

    /**
     * 获得当前日期的字符串型
     *
     * @return
     */
    public static String getCurrentDateString() {
        return sdf.format(new Date());
    }

    /**
     * 默认初始日期
     *
     * @param date
     * @return
     */
    public static Date defaultStartDate(Date date) {
        return date == null ? getTodayPlus2Date() : date;
    }

    /**
     * 默认结束日期
     *
     * @param date
     * @return
     */
    public static Date defaultEndDate(Date date) {
        return date == null ? getTodayPlus1Year() : date;
    }

    /**
     * 两个时间之间的天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long diffDays(Date startDate, Date endDate) {
        return Math.abs(startDate.getTime() - endDate.getTime()) / (24 * 60 * 60 * 1000);
    }

    /**
     * 带顺序的两个时间的天数
     * @param startDate
     * @param endDate
     * @param sort
     * @return
     */
    public static long diffDays(Date startDate, Date endDate, boolean sort) {
        return sort ? (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000) :
                diffDays(startDate, endDate);
    }

    /**
     * 从某一天到今天的天数
     *
     * @param date
     * @return
     */
    public static long diffDayAndToday(Date date) {
        return diffDays(date, new Date(), true);
    }

    /**
     * 从今天到某一天的天数
     *
     * @param date
     * @return
     */
    public static long diffTodayAndDay(Date date) {
        return diffDays(new Date(), date, true);
    }

    /**
     * Date转成String
     *
     * @param date
     * @return
     */
    public static String convertDateToString(Date date) {
        return sdf.format(date);
    }

}
