package com.example.demo.alice.user.mapper;

import com.example.demo.alice.user.domain.AliceUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

//用户相关信息的mapper
@Mapper
public interface AliceUserMapper {

    //创建一个新的用户
    int createUser(AliceUser user);

    //根据用户名或者id查询是否存在这个用户
    AliceUser selectUser(AliceUser user);

    //判断系统中用户名是否已经存在
    int isExist(AliceUser user);

    //判断系统中电话登录的用户名是否已经存在
    int isPhoneNumberExist(String phonenumber);

    //登录接口，让用户进行登录，成功之后需要返回一个token给前端，参考周哥项目
    //返回一个user实体类，传入实体类,注意mybatis中比较的是加密后的password
    AliceUser login(AliceUser user);

    //通过用户对应信息来查询该用户对应的盐
    String getSalt(AliceUser user);

    //通过用户对应信息查看用户的status状态
    String getStatus(AliceUser user);

    //通过用户信息查看ban_time （status为1的情况是封禁状态）
    Date getBanTime(AliceUser user);
    //修改status为0正常使用状态
    void changeStatus(AliceUser user);



    /**
    * 下面的内容是有关在线表的
    * */
    //将用户添加到在线表格中
//    int insertOnlineUser(AliceUser user);





}
