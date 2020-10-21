package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <h3>demo</h3>
 * <p>User 对应数据库模型</p>
 *
 * @author : CTL
 * @date : 2020-10-14 11:25
 */
//为模型
//@Entity
//@Table(name="user")
public class UserModel {
    //建立映射关系
//    @Id
    private Integer id;
    private Integer age;
    private String name;
    private String email;
    private String address;
    private String username;
    private String password;

    //快速构建构造方法,给之后新增等地方调用,构造函数参数的顺序必须与数据库中的字段顺序一样
    public UserModel(Integer id, String name,Integer age, String email, String address, String username, String password) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.email = email;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
