package com.example.demo;

import com.example.demo.common.interceptor.LoginInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.slf4j.LoggerFactory;

@SpringBootApplication
@ServletComponentScan
@MapperScan({"com.example.demo.mapper","com.example.demo.admin.author.mapper",
             "com.example.demo.admin.pythontest.mapper",
             "com.example.demo.admin.update.mapper",
             "com.example.demo.admin.roadInfoTest.mapper",
             "com.example.demo.admin.jkyYoushibi.mapper",
             "com.example.demo.alice.user.mapper",
             "com.example.demo.alice.announcement.mapper",
             "com.example.demo.alice.goodthings.mapper",
             "com.example.demo.alice.*.mapper"})
@EnableCaching  //这是缓存的启动注解，开启缓存
@EnableTransactionManagement   /// 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@EnableScheduling //启动定时器的注解，表示启动定时器任务
public class DemoApplication implements WebMvcConfigurer { //让启动类实现了接口开启全局的token验证
    //启动日志,如果是静态的加上了static ，那么不能调用Object 的getClass()方法，所以在getLogger里面不能使用
    //如果没有加static ，那么Logger里面可以用getClass()方法
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("恭喜！Alice已经启动成功了，sakura！");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registration){
        //使用日志
        logger.info("SpringBoot开始加载");
        registration.addInterceptor(new LoginInterceptor());

        //使用日志
        logger.info("SpringBoot加载完毕");


        //Object s = getClass();
//        Object d= DemoApplication.class;
//        System.out.println("这个object是啥"+s);
//        System.out.println("这又是啥"+d);
//        System.out.println("是否相等"+s==d);
    }

}
