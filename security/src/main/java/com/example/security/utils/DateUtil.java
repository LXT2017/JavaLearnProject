package com.example.security.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author shawn
 * @version 1.0
 * @ClassName DateUtil
 * Description:
 * @date 2023/2/28 21:49
 */
public class DateUtil {

    private final static SimpleDateFormat SDF_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final SimpleDateFormat SDF_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取当前时间的YYYY-MM-DD HH:mm:ss格式
     */
    public static String getTime() {
        return SDF_TIME.format(new Date());
    }

    /**
     * 日期比较，如果s>=e 返回true 否则返回false
     */
    public static boolean compareDate(String s, String e) {
        if(fomatDate(s)==null||fomatDate(e)==null){
            return false;
        }
        return s.compareTo(e)>0;
    }
    /**
     * 日期比较，如果s>=e 返回true 否则返回false
     */
    public static boolean compareDate(Date s, Date e) {
        if(s==null||e==null){
            return false;
        }
        return  s.getTime()-e.getTime()<=5*60*1000;
    }

    /**
     * 格式化日期
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前时间的后i天
     */
    public static String getAddDay(int i){
        String currentTime = DateUtil.getTime();
        GregorianCalendar gCal = new GregorianCalendar(
                Integer.parseInt(currentTime.substring(0, 4)),
                Integer.parseInt(currentTime.substring(5, 7)) - 1,
                Integer.parseInt(currentTime.substring(8, 10)));
        gCal.add(GregorianCalendar.DATE, i);
        return SDF_DATE_FORMAT.format(gCal.getTime());
    }

    /**
     * 获取当前时间的后i天 精确到秒
     */
    public static String getAddDayTime(int i){
        Date date = new Date(System.currentTimeMillis()+ (long) i *24*60*60*1000);
        return SDF_TIME.format(date);
    }

    /**
     * 获取当前时间的+多少秒 精确到秒
     */
    public static String getAddDaySecond(int i){
        Date date = new Date(System.currentTimeMillis()+i* 1000L);
        return SDF_TIME.format(date);
    }
}