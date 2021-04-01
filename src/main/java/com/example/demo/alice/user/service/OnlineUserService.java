package com.example.demo.alice.user.service;

import com.example.demo.alice.user.domain.AliceUser;

import javax.servlet.http.HttpServletRequest;

//在线用户操作相关
public interface OnlineUserService {

    //向在线用户表插入一条数据
    String insertOnlineUser(HttpServletRequest request, AliceUser user);

    //向用户登录日志表插入一条信息
    String insertLoginInfor(HttpServletRequest request, AliceUser user,String status,String msg);
}
