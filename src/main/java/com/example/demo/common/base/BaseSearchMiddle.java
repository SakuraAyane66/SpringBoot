package com.example.demo.common.base;

import java.util.Date;

/**
 * @author CTL
 * <p>搜索过渡用的实体类，用作过渡使用 </p>
 * 创建日期：2021-04-12 16:42
 */
public class BaseSearchMiddle {
    private Integer id; //搜索用id
    private String username;
    private String name;
    private Date beforeDate;
    private Date afterDate;
    private String keyword; //关键词

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeforeDate() {
        return beforeDate;
    }

    public void setBeforeDate(Date beforeDate) {
        this.beforeDate = beforeDate;
    }

    public Date getAfterDate() {
        return afterDate;
    }

    public void setAfterDate(Date afterDate) {
        this.afterDate = afterDate;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "BaseSearchMiddle{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", beforeDate=" + beforeDate +
                ", afterDate=" + afterDate +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
