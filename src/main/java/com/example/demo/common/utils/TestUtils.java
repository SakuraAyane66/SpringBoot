package com.example.demo.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestUtils {
    public static void main(String[] args) {
        //各种测试main代码
//        List<String> list = new ArrayList<>();
//        list.add("第一个");
//        list.add("第er个");
//        list.add("第三个");
////        for(String ll:list){
////            System.out.println("?"+ll);
////        }
//        for(int i=0;i<list.size();i++){
//            System.out.println("第"+i+"个是?"+list.get(i));
//        }
//        System.out.println("是否包含？"+list.contains("第一个"));
//
//        List<Integer> list1 = new ArrayList<>();
//        list1.add(1);
//        list1.add(66);
//        System.out.println(list1.contains(66));
//        System.out.println("size?"+list.size());
//
//        System.out.println("第m个是"+list.get(2));
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        Double min=3.4;
        Double max=4.0;
        Random random = new Random();
        List<String> list = new ArrayList<>();
        for(int i=0;i<3000;i++){
            if(list.size()>=100){
                break;
            }
            double r = random.nextDouble()*3+8;
//            System.out.println("r是什么？"+r);
//            System.out.println("r<=4???"+(r<=4));
            if(r<=9.83&&r>=9.45){
                list.add(df.format(r));
            }

        }
        System.out.println("?"+list.size());
        for(int j=0;j<list.size();j++){
            System.out.print(list.get(j)+",");
        }

    }
}
