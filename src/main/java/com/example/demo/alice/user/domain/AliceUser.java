package com.example.demo.alice.user.domain;

import java.util.Date;

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
    private String school;
    private String email;
    private String qianming;
    private String phonenumber;
    private String icons;
    private String salt; //盐加密
    //这里的密码是加密前的
    private String clearPassword;
    //重复密码，第一次登录时使用
    private String reclearPassword;
    private String login_ip; //最后登录ip
    private Date login_data; //最后登录时间
    private Date creat_time; //创建时间
    private Date update_time; //最后进行修改时间
    private String remark; //备注
    private Date ban_time; //解禁时间，需要这个时间后账号才能使用
    private String status; //是否注销


    public AliceUser(){} //无参构造
    //全参数构造就不写了

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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getLogin_ip() {
        return login_ip;
    }

    public void setLogin_ip(String login_ip) {
        this.login_ip = login_ip;
    }

    public Date getLogin_data() {
        return login_data;
    }

    public void setLogin_data(Date login_data) {
        this.login_data = login_data;
    }

    public Date getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(Date creat_time) {
        this.creat_time = creat_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getBan_time() {
        return ban_time;
    }

    public void setBan_time(Date ban_time) {
        this.ban_time = ban_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getReclearPassword() {
        return reclearPassword;
    }

    public void setReclearPassword(String reclearPassword) {
        this.reclearPassword = reclearPassword;
    }

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }

    //输出还是要写的
    @Override
    public String toString() {
        return "AliceUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", xingmin='" + xingmin + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", school='" + school + '\'' +
                ", email='" + email + '\'' +
                ", qianming='" + qianming + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", salt='" + salt + '\'' +
                ", clearPassword='" + clearPassword + '\'' +
                ", login_ip='" + login_ip + '\'' +
                ", login_data=" + login_data +
                ", creat_time=" + creat_time +
                ", update_time=" + update_time +
                ", remark='" + remark + '\'' +
                ", ban_time=" + ban_time +
                ", status='" + status + '\'' +
                '}';
    }
}
