package com.example.demo.alice.user.mapper;

import com.example.demo.alice.user.domain.OnlineUser;
import org.apache.ibatis.annotations.Mapper;

//在线用户相关的mapper和登录日志mapper
@Mapper
public interface OnlineUserMapper {
    //向在线用户表中插入一个用户
    int insertOnlineUser(OnlineUser onlineUser);

    //向操作记录表中插入一条记录
    int insertLoginInfor(OnlineUser onlineUser);
}
