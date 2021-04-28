package com.example.demo.common.utils;

import com.example.demo.DemoApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.util.resources.LocaleData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

/**
 * @author CTL
 * <p>从系统的currenttime转为年月日等格式 </p>
 * 创建日期：2020-10-30 14:52
 */
public class DateTransUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateTransUtil.class);
    //转化为年月日 小时分秒格式，传入的是时间戳
    public static String getDateTime(Long currentTime){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(currentTime);
//        System.out.println("what is"+date);
//        System.out.println(formatter.format(date));
        return formatter.format(date);
    }
    //直接获取系统当前的年份
    public static int getYear(){
        //通过LocalDate类获取
        LocalDate today = LocalDate.now();
        System.out.println(today);
        return today.getYear();
    }
    //通过传入Date格式的时间，获取date的年份
    public static int getYear(Date date){
        String year = String.format("%tY",date);
        try {
            int a = Integer.parseInt(year);
            return a;
        } catch (NumberFormatException e) {
            logger.info("data获取年份出现错误",date);
            e.printStackTrace();
            return -1;
        }
    }
    //通过传入date转为String类型
    public static String getDateTime(Date date){
        //格式化为yyyy-mm-dd hh:mm:ss类型
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    //把转换好的String类型转回date类型
    public static Date changeTodate(String time){

        return null;
    }
    //比较两者date的时间大小，传入的是2个String类型
    public static boolean compareTime(String time1,String time2){
        //比较的是time1时间是否在time2的时间之后（如果time1在time2之后返回true，否则返回false）
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = format.parse(time1);
            Date date2 = format.parse(time2);
            int compareTo = date1.compareTo(date2);
            //如果大于等于0，表示time1在time2时间之后
            if(compareTo>=0){
                System.out.println(compareTo);
                return true;
            }
            else{return false;}
        } catch (ParseException e) {
            logger.info("比较日期出现错误",e);
            e.printStackTrace();
        }
        return false;
    }

    //根据传入的String时间，和String的格式时长x，返回前x秒的时间，不涉及跨天
    public static String getPreTime(String time,String x){


        return "";
    }
    public static void main(String[] args) {
        DateTransUtil.getDateTime(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = "2021-03-30 11:27:53";
        try{
            Date date1 = df.parse(time);
            System.out.println("date是什么"+date1);
        }catch (ParseException e){ e.printStackTrace();}
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
    }
}
