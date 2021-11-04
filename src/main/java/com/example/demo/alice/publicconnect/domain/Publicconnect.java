package com.example.demo.alice.publicconnect.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CTL
 * <p>公益链接实体类 </p>
 * 创建日期：2021-05-21 15:37
 */
@Data  //提供set ,get，toString等方法，
@NoArgsConstructor  //无参数构造器
public class Publicconnect {
    //主键id
    private int id;
    //topic标题
    private String topic;
    //date时间
    private Data time;
    //author作者
    private String author;
    //hyperlink超链接地址
    private String hyperlink;
    private final Integer db =2;
    public Integer getDb() {
        return db;
    }
}
