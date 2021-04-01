package com.example.demo.alice.user.service;


import com.example.demo.alice.user.domain.AliceUser;

import java.util.Map;

public interface AliceUserService {
    //通过usernma和password创建新的用户,返回是否成功创建
    String createUser(AliceUser user);
    //通过phonenumber创建新的用户，返回是否成功信息
    String creatUserByPhoneNumber(AliceUser user);
    //登录接口，判断用户是否能成功登录
    Map<String,String> login(AliceUser user);


}
