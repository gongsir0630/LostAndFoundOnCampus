package com.gongsir.wxapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 龚涛
 * @date 2019/10/25 13:46
 * 编码不要畏惧变化，要拥抱变化
 */
public class TimeUtil {
    /**
     * 根据所给日期返回两日期相差的秒数
     * @param d1 开始时间
     * @param d2 结束时间
     * @return 返回两个日期间隔的毫秒数
     */
    private static long getSecond(Date d1, Date d2)
    {
        long a1 = d1.getTime();
        long a2 = d2.getTime();
        long a3 = (a1 - a2)/1000;
        return a3;
    }

    /**
     * 根据秒数,计算相差的时间并以**时**分**秒返回
     * @param m 秒数
     * @return **时**分**秒
     */
    private static String getBeapartDate(long m)
    {
        String beapartdate="";
        int nDay = (int)m/(24*60*60);
        int nHour = (int)(m-nDay*24*60*60)/(60*60);
        int nMinute = (int)(m-nDay*24*60*60-nHour*60*60)/60;
        int nSecond = (int)m-nDay*24*60*60-nHour*60*60-nMinute*60;
        beapartdate = nDay +"天"+nHour+"小时"+nMinute+"分"+nSecond+"秒";
        return beapartdate;
    }

    public static void main(String[] args) throws ParseException
    {
        Date now=new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time =  new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Date end = f.parse("2009-10-09 16:12:11");
        Date begin=   f.parse("2009-09-09 10:10:10");
        String xx = getBeapartDate(getSecond(end,begin));
        System.out.println(xx);
    }
}





