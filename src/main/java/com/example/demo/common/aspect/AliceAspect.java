package com.example.demo.common.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 切面，是自定义注解对应的切面
 */
@Aspect //表示这是一个切面类
@Component
@SuppressWarnings({"unused"})  //忽略的警告
public class AliceAspect {
    //引入slf4j的Logger
    protected Logger logger = LoggerFactory.getLogger(getClass());
    //下面的写法也可以，上面是项目的写法，下面是网上代码的写法，打印出来结果相等，但是本身不能比较
//    protected Logger logger1 = LoggerFactory.getLogger(AliceAspect.class);

    public static final String TOKEN_KEY = "token";

//    public static void main(String[] args) {
//        AliceAspect a1= new AliceAspect();
//        System.out.println("相等么？"+a1.logger);
//       System.out.println("----------"+a1.logger1);   //打印出来的结果相等，但是本身不能比较
//    }

}
