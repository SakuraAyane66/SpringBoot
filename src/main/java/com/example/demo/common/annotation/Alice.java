package com.example.demo.common.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 */


@Target(ElementType.METHOD) //定义注解作用目标，这里是方法，具体可以指定的参数见网上
@Retention(RetentionPolicy.RUNTIME) //注解的注解，元注解，指定注解保留多久（3种模式，源文件，class文件，一直保持，这儿是第三种运行时也存在）
@Documented //指定注解被写入文档
public @interface Alice {
    String value() default "";
}
