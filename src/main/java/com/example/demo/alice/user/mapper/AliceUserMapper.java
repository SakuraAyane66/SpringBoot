package com.example.demo.alice.user.mapper;

import com.example.demo.alice.user.domain.AliceUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AliceUserMapper {

    //创建一个新的用户
    void createUser(AliceUser user);

    //判断系统中用户名是否已经存在
    int isExist(AliceUser user);

    //登录接口，让用户进行登录，成功之后需要返回一个token给前端，参考周哥项目
    //返回一个user实体类，传入实体类,注意mybatis中比较的是加密后的password
    AliceUser login(AliceUser user);
}
