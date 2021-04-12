package com.example.demo.alice.announcement.domain;

import java.util.Date;

/**
 * @author CTL
 * <p>公告实体类 </p>
 * 创建日期：2021-04-12 10:59
 */
public class Announcement {
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

    public Announcement(){} //无参构造器
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Announcement(Integer id, String topic, Date time, String content, String author) {
        this.id = id;
        this.topic = topic;
        this.time = time;
        this.content = content;
        this.author = author;
    }

    @Override
    public String toString() {
        return "announcement{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", time=" + time +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
