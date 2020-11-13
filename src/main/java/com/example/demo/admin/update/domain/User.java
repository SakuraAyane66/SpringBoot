package com.example.demo.admin.update.domain;

import com.example.demo.common.base.BaseEntity;

import java.io.Serializable;

/**
 * @author CTL
 * <p>excel里面的User表，对应的是数据库里面的user表 </p>
 * 创建日期：2020-11-02 10:28
 */
public class User extends BaseEntity implements Serializable {
    //用户id
    private Integer id;
    //用户name
    private String name;
    private int age;
    private String email;
    private String address;
    private String username;
    private String password;
    private int belong; //新增了所属文件，用于判断excel数据在数据库的唯一标识
    public User(){};

    public int getBelong() {
        return belong;
    }

    public void setBelong(int belong) {
        this.belong = belong;
    }

    public User(Integer id, String name, int age, String email, String address, String username, String password, int belong) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
        this.username = username;
        this.password = password;
        this.belong = belong;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "id:" +this.id+
                "  name:" +this.name+
                "  age:"+this.age+
                "  email:" +this.email+
                "  address:" +this.address+
                "  username:" +this.username+
                "  password:" +this.password+
                "";
    }
}
