package com.ximouzhao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

    public static final String DATE_TIME_FORMAT_STR ="yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_STR ="yyyy-MM-dd";

    /**
     * 通过默认的"yyyy-MM-dd HH:mm:ss"格式化给定的日期时间字符串
     * @param dateTimeStr 时间字符串
     * @return Date对象
     */
    public static Date defaultFormatDateTime( String dateTimeStr){
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT_STR);
        Date date=null;
        try {
            date=sdf.parse(dateTimeStr);
        } catch (ParseException e) {
            throw new RuntimeException(new String("日期格式化失败"));
        }
        return date;
    }

    /**
     * 通过默认的"yyyy-MM-dd"格式化给定的日期字符串
     * @param dateTimeStr 日期字符串
     * @return Date对象
     */
    public static Date defaultFormatDate( String dateTimeStr){
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_STR);
        Date date=null;
        try {
            date=sdf.parse(dateTimeStr);
        } catch (ParseException e) {
            throw new RuntimeException(new String("日期格式化失败"));
        }
        return date;
    }

    /**
     * 通过formatStr格式化给定的dateTimeStr时间字符串
     * @param dateTimeStr 时间字符串
     * @param formatStr 格式化pattern字符串
     * @return Date对象
     */
    public static Date format( String dateTimeStr, String formatStr){
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        Date date=null;
        try {
            date=sdf.parse(dateTimeStr);
        } catch (ParseException e) {
            throw new RuntimeException(new String("日期格式化失败"));
        }
        return date;
    }

    /**
     * 通过formatStr格式化给定的dateTimeStr时间字符串
     * @param date 时间
     * @param formatStr 格式化pattern字符串
     * @return Date对象
     */
    public static String formatDate( Date date,  String formatStr){
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }

    /**
     * 获取days天的00:00:00时间，比如days为-1时，返回时间为前一天的00:00:00 2019-10-20 00:00:00
     * @param days 天数，负数为之前的天数，正数是向后的天数
     * @return days天之前的00:00:00时间
     */
    public static Date getDateTimeStartOfDay(int days){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,days);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND,0);
        Date startTime = calendar.getTime();
        return startTime;
    }
    /**
     * 获取days天的23:59:59时间，比如days为-1时，返回时间为前一天的 2019-10-20 23:59:59
     * @param days 天数，负数为之前的天数，正数是向后的天数
     * @return days天之前的23:59:59时间
     */
    public static Date getDateTimeEndOfDay(int days){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,days);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND,999);
        Date startTime = calendar.getTime();
        return startTime;
    }
    public static String toDefaultDateTimeString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT_STR);
        return formatter.format(date);
    }
    public static String toDefaultDateString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_STR);
        return formatter.format(date);
    }
    public static String getFirstDayOfMonth(String month){
        String[] date = month.split("-");
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,Integer.parseInt(date[0]));
        //设置月份
        cal.set(Calendar.MONTH,Integer.parseInt(date[1])-1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);

        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }
    public static String getLastDayOfMonth(String month){
        String[] date = month.split("-");
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,Integer.parseInt(date[0]));
        //设置月份
        cal.set(Calendar.MONTH,Integer.parseInt(date[1])-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 返回较大的日期，空值参与比较
     * @param date1
     * @param date2
     * @return
     */
    public static Date max(Date date1,Date date2){
        if(date1==null&&date2==null){
            return null;
        }
        if(date1 == null){
            return date2;
        }
        if(date2 == null){
            return date1;
        }
        if(date1.compareTo(date2)>0){
            return date1;
        }else {
            return date2;
        }
    }

}
