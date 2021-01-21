package com.example.demo.alice.user.mapper;

import com.example.demo.alice.user.domain.AliceUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AliceUserMapper {

    //创建一个新的用户
    void createUser(AliceUser user);
}
