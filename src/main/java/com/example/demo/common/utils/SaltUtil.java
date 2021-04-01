package com.example.demo.common.utils;

/**
 * @author CTL
 * <p>随机生成盐 </p>
 * 创建日期：2021-03-26 16:05
 */
public class SaltUtil {
    public static String creatSalt(int x){
        String Result = "";
        //从0~9，a~z中随机
        char[] Str = "0123456789abcdefghijkmlnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (int i = 0; i < x; i++) {
            int index = (int) (Math.random() * Str.length);
            Result += Str[index];
        }
        return Result;
    }

//    public static void main(String[] args) {
//        System.out.println(SaltUtil.creatSalt(6));
//    }
}
