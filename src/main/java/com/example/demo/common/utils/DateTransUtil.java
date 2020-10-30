package com.example.demo.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author CTL
 * <p>从系统的currenttime转为年月日等格式 </p>
 * 创建日期：2020-10-30 14:52
 */
public class DateTransUtil {
    //转化为年月日 小时分秒格式
    public static String getDateTime(Long currentTime){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒");
        Date date = new Date(currentTime);
        System.out.println(formatter.format(date));
        return formatter.format(date);
    }

//    public static void main(String[] args) {
//        DateTransUtil.getDateTime(System.currentTimeMillis());
//    }
}
