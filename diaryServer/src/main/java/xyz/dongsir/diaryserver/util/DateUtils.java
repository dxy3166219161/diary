package xyz.dongsir.diaryserver.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static final String STANDARD_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 将日期转化为Calendar
     * @param day
     * @return
     */
    public static Calendar transformCalendar(Date day) {
        Calendar c = Calendar.getInstance();
        if (day != null) {
            c.setTime(day);
        }
        return c;
    }

    /**
     * 获取年份
     * @param date
     * @return
     */
    public static Integer getYear(Date date){
        return transformCalendar(date).get(Calendar.YEAR);
    }

    /**
     * 获取月份
     * @param date
     * @return
     */
    public static Integer getMonth(Date date){
        return transformCalendar(date).get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期号数
     * @param date
     * @return
     */
    public static Integer getDay(Date date){
        return transformCalendar(date).get(Calendar.DATE);
    }

    /**
     * 获取小时数
     * @param date
     * @return
     */
    public static Integer getHours(Date date){
        return transformCalendar(date).get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取分钟
     * @param date
     * @return
     */
    public static Integer getMinute(Date date){
        return transformCalendar(date).get(Calendar.MINUTE);
    }

    /**
     * 获取秒
     * @param date
     * @return
     */
    public static Integer getSecond(Date date){
        return transformCalendar(date).get(Calendar.SECOND);
    }

    public static String formatDate(Date date, String format) {
        Date dateTime = date;
        if (dateTime == null) {
            dateTime = new Date();
        }
        String strFormat = format;
        SimpleDateFormat formatter = new SimpleDateFormat(strFormat);
        return formatter.format(dateTime);
    }

    public static void main(String[] args) {
        Integer month = getSecond(new Date());
        System.out.println(month);
    }
}
