package com.example.demo;

import com.example.demo.config.LoginInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.slf4j.LoggerFactory;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.example.demo.mapper")
@EnableCaching  //这是缓存的启动注解，开启缓存
@EnableTransactionManagement   /// 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
public class DemoApplication implements WebMvcConfigurer { //让启动类实现了接口开启全局的token验证
    //启动日志
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registration){
        //使用日志
        logger.info("SpringBoot开始加载");
        registration.addInterceptor(new LoginInterceptor());
        //使用日志
        logger.info("SpringBoot加载完毕");
        System.out.println("恭喜！Alice已经启动成功了，sakura！");
    }

}
