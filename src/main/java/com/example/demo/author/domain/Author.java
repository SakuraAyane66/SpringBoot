package com.example.demo.author.domain;
import com.example.demo.model.UserModel;

import javax.persistence.Transient;

/**
 * @author CTL
 * <p>Author实体类对象 </p>
 * 创建日期：2020-10-23 16:35
 */
public class Author {
    /**
     *  author id主键
     */
    private Integer id;
    /**
     *  name 名字
     */
    private String name;
    /**
     *  外键 userId
     */
    private Integer user_id;
    /**
     *  地址 addr
     */
    private String addr;
    //伪字段，不在数据库表中的字段
    @Transient
    private Integer u_id; //表user中的主键，id
    @Transient
    private String u_name; //表user中的name
    @Transient
    private int age;      //age
    @Transient
    private String email; //email
    @Transient
    private String address; //表user中的address
    @Transient
    private String username; //表user中的username

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Transient
    private String password; //表user中的password

    //userModel对象，貌似没有这样使用的，使用的是column +property
    private UserModel user;
    //无参构造器
    public Author(){};
    //形参构造器
    public Author(Integer id, String name, Integer user_id, String addr) {
        this.id = id;
        this.name = name;
        this.user_id = user_id;
        this.addr = addr;
    }
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

}
