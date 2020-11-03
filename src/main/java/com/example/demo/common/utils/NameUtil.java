package com.example.demo.common.utils;

/**
 * @author CTL
 * <p>通过name和i，返回一个名字带有i的变量名 </p>
 * 失败了，这个工具不可用，也不能作为变量声明
 * 创建日期：2020-11-02 11:30
 */
public class NameUtil {
    public static String setName(String name,int i){
        String s = name+i+"";
        return s;
    }

    public static void main(String[] args) {
//        String cell = "cel";
//        System.out.println(NameUtil.setName(cell,2));
        for(int i=0;i<99;i++){
            System.out.println( NameUtil.setName("test",i));
        }
    }
}
