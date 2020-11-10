package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author CTL
 * <p>studentname实体类 </p>
 * 创建日期：2020-10-16 10:28
 */
public class StudentForm implements Serializable {
    private int id;
    private String account;
    private int age;
    private String country;
    private String name;
    private String password;
    //新加了 token expireTime和LoginTime (后面删除了，因为有问题，data时间转过来有问题)
    private String token;
    /**
     * token 过期时间
     */
    private LocalDateTime expireTime;
    /**
     *  登录时间
     */
    private LocalDateTime loginTime;
    /**
     *  过期时间
     */
    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    //顺序要和数据库表顺序对应
    public StudentForm(int id, String account, int age, String country, String name, String password) {
        this.id = id;
        this.account = account;
        this.age = age;
        this.country = country;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
