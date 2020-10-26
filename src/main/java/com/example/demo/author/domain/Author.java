package com.example.demo.author.domain;
import com.example.demo.model.UserModel;
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
     *  name userId
     */
    private String name;
    /**
     *  外键 userId
     */
    private Integer user_id;
    /**
     *  地址 address
     */
    private String address;
    //伪字段，不在数据库表中的字段
    //userModel对象
    private UserModel user;

    //形参构造器
    public Author(Integer id, String name, Integer user_id, String address) {
        this.id = id;
        this.name = name;
        this.user_id = user_id;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
