package com.example.demo.alice.publicactivity.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author CTL
 * <p>公益活动实体类 </p>
 * 创建日期：2021-05-21 15:45
 */
@Data
@NoArgsConstructor
public class Publicactivity {
    private int id;
    private String topic;
    private String content;
    private Date time;
    private String author;
    private String hyperlink;
}
