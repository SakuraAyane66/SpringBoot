package com.example.demo.alice.user.service;


import com.example.demo.alice.user.domain.AliceUser;

public interface AliceUserService {
    //创建新的用户,返回是否成功创建
    String createUser(AliceUser user);

    //登录接口，判断用户是否能成功登录
    String login(AliceUser user);
}
