package com.example.demo.alice.goodthings.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author CTL
 * <p>好人好事实体类 </p>
 * 创建日期：2021-04-15 17:01
 */
@Data //注解，让实体类拥有set和get，toString等方法
@NoArgsConstructor //该类拥有无参构造器
public class Goodthings {
    //主键id
    private Integer id;
    //topic标题
    private String topic;
    //生成时间
    private Date time;
    //content内容
    private String content;
    //author作者
    private String author;
}
