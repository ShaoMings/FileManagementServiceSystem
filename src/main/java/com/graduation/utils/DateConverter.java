package com.graduation.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Description 日期转换工具
 *
 * @author shaoming
 * @date 2021/8/16 16:47
 * @since 1.0
 */
public class DateConverter {

    /**
     * 以当前时间并用指定的格式进行格式化
     *
     * @param format 指定格式
     * @return 当前时间 格式化后的时间
     */
    public static String getFormatDate(String format) {
        // 判断格式为空
        if (StringUtils.isBlank(format)) {
            // 指定默认格式
            format = "yyyy-MM-dd HH:mm:ss";
        }
        return new SimpleDateFormat(format).format(new Date());
    }


    /**
     * 通过传入的date 用yyyy-MM-dd HH:mm:ss的格式进行格式化
     *
     * @param date 传入日期时间
     * @return 格式化后的时间
     */
    public static String getFormatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 通过传入的时间 以及格式 进行时间格式化
     *
     * @param date   时间
     * @param format 格式
     * @return 格式化后的时间
     */
    public static String getFormatDate(Date date, String format) {
        // 判断格式为空
        if (StringUtils.isBlank(format)) {
            // 指定默认格式
            format = "yyyy-MM-dd HH:mm:ss";
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 将字符串转为日期时间类
     *
     * @param str 字符串
     * @return 日期时间类
     */
    public static Date StringToDate(String str) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 将字符串转为指定格式的日期时间类
     *
     * @param str    字符串
     * @param format 格式
     * @return 日期时间类
     */
    public static Date StringToDate(String str, String format) {
        Date date = null;
        if (StringUtils.isBlank(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        try {
            date = new SimpleDateFormat(format).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 比较两个日期的大小
     *
     * @param d1 日期1
     * @param d2 日期2
     * @return 1表示 日期1 晚于 日期2  0表示相等  -1表示日期1 早于 日期2
     */
    public static int compareDate(Date d1, Date d2) {
        return Long.compare(d1.getTime(), d2.getTime());
    }

    /**
     * 用于获取时间戳
     *
     * @return 当前时间秒数
     */
    public static String getTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }


    /**
     * 将时间戳转为指定格式日期时间
     * @param seconds 时间戳
     * @param format  日期时间格式
     * @return  指定格式日期时间
     */
    public static String timeStampToDate(String seconds,String format){
        if (seconds == null || seconds.isEmpty() || Constant.NULL_VALUE.equals(seconds)){
            return "";
        }
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.parseLong(seconds+"000")));
    }


    /**
     * 计算两个日期之间相差的天数
     *
     * @param eDate            较早(小)的日期
     * @param lDate            较晚(大)的日期
     * @param isContainEndDate 是否包含结束日期  包含则多加1
     * @return 天数之差
     */
    public static int differentBetweenDays(Date eDate, Date lDate, Boolean isContainEndDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return differentBetweenDays(sdf.format(eDate), sdf.format(lDate), isContainEndDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算两个字符串格式的日期之间相差的天数
     *
     * @param eDate            较早(小)的日期
     * @param lDate            较晚(大)的日期
     * @param isContainEndDate 是否包含结束日期  包含则多加1
     * @return 天数之差
     */
    public static int differentBetweenDays(String eDate, String lDate, Boolean isContainEndDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return days(sdf.parse(eDate), sdf.parse(lDate), isContainEndDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 月份相差天数计算辅助类
     *
     * @param eDate            较早(小)的日期
     * @param lDate            较晚(大)的日期
     * @param isContainEndDate 是否包含结束日期  包含则多加1
     * @return 相差天数
     * @throws Exception 异常
     */
    private static int days(Date eDate, Date lDate, Boolean isContainEndDate) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(eDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(lDate);
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / (1000 * 3600 * 24) + (isContainEndDate ? 1 : 0);
        return Integer.parseInt(String.valueOf(betweenDays));
    }


    /**
     *  将传入的字符串格式日期 进行偏移天数计算  正数表示天数向后加 反正则减
     * @param dateString 日期字符串
     * @param days 偏移天数
     * @return 计算后的日期字符串
     */
    public static String dayCalculate(String dateString, int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = new GregorianCalendar();
        assert date != null;
        calendar.setTime(date);
        calendar.add(Calendar.DATE,days);
        date = calendar.getTime();
        return sdf.format(date);
    }


    /**
     *  将当前日期 进行偏移天数计算  正数表示天数向后加 反正则减
     * @param days 偏移天数
     * @return 计算后的日期字符串
     */
    public static String dayCalculateBaseOnNow(int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = sdf.parse(getFormatDate("yyyy-MM-dd HH:mm"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = new GregorianCalendar();
        assert date != null;
        calendar.setTime(date);
        calendar.add(Calendar.DATE,days);
        date = calendar.getTime();
        return sdf.format(date);
    }



}
