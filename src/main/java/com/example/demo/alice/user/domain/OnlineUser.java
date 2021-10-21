package com.example.demo.alice.user.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author CTL
 * <p>在线用户的实体类（也是登录记录的实体类） </p>
 * 创建日期：2021-03-31 15:56
 */
@Data
public class OnlineUser {
    /** 用户的username*/
    private String username;
    /** 用户的ip地址*/
    private String ipaddr;
    /** 登录地址*/
    private String loginLocation;
    /** 浏览器类型*/
    private String browser;
    /** 操作系统*/
    private String os;
    /** 登录状态,0为下线，1为成功，同时也作为syy_login_infor日志表的实体类*/
    private String status;
    /** 最后登录时间*/
    private Date loginTime;
    /** 最后访问时间*/
    private Date lastAccessTime;

    /** 登录相关信息 */
    private String msg;
    //无参构造器
    public OnlineUser(){}
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OnlineUser{" +
                "username='" + username + '\'' +
                ", ipaddr='" + ipaddr + '\'' +
                ", loginLocation='" + loginLocation + '\'' +
                ", browser='" + browser + '\'' +
                ", os='" + os + '\'' +
                ", loginTime=" + loginTime +
                ", lastAccessTime=" + lastAccessTime +
                ", msg='" + msg + '\'' +
                '}';
    }
}
