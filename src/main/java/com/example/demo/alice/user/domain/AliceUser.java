package com.example.demo.alice.user.domain;

/**
 * @author CTL
 * <p>用户类 </p>
 * 创建日期：2021-01-21 00:17
 */
public class AliceUser {
    private Integer id;
    private String username;
    //这里的密码是加密后的
    private String password;
    private String xingmin;
    private int age;
    private String address;
    private String email;
    private String qianming;
    //这里的密码是加密前的
    private String clearPassword;

    public AliceUser(){} //无参构造
    public AliceUser(Integer id, String username,
                     String password, String xingmin,
                     int age, String address, String email,
                     String qianming, String clearPassword) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.xingmin = xingmin;
        this.age = age;
        this.address = address;
        this.email = email;
        this.qianming = qianming;
        this.clearPassword = clearPassword;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getXingmin() {
        return xingmin;
    }

    public void setXingmin(String xingmin) {
        this.xingmin = xingmin;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQianming() {
        return qianming;
    }

    public void setQianming(String qianming) {
        this.qianming = qianming;
    }

    public String getClearPassword() {
        return clearPassword;
    }

    public void setClearPassword(String clearPassword) {
        this.clearPassword = clearPassword;
    }

    @Override
    public String toString() {
        return "AliceUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", xingmin='" + xingmin + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", qianming='" + qianming + '\'' +
                ", clearPassword='" + clearPassword + '\'' +
                '}';
    }
}
