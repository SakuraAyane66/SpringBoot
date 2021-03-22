package com.example.demo.alice.user.service.impl;

import com.example.demo.alice.user.domain.AliceUser;
import com.example.demo.alice.user.mapper.AliceUserMapper;
import com.example.demo.alice.user.service.AliceUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.alibaba.druid.util.Utils.md5;

/**
 * @author CTL
 * <p>service层 </p>
 * 创建日期：2021-01-21 00:42
 */
@Service
public class AliceUserServiceImpl implements AliceUserService {
    @Resource
    private AliceUserMapper mapper;

    //创建一个新用户
    @Override
    public String createUser(AliceUser user) {
        int isExist = mapper.isExist(user);
        //不存在，记录条数为0
        if(isExist==0){
            user.setClearPassword(user.getPassword());
            user.setPassword(md5(user.getClearPassword())); //对明文密码加密
            mapper.createUser(user);
            return "创建成功！";
        }else{
            return "用户名已存在，请换个名字！";
        }
    }

    @Override
    public String login(AliceUser user) {
        //判断是否存在这个用户
        int isExist = mapper.isExist(user);
        if(isExist==0){
            //用户名不存在
            return "该用户名不存在!请检查用户名！";
        }
        //加密初始密码
        user.setPassword(md5(user.getPassword()));
        AliceUser aliceUser = mapper.login(user);//判断是否有这个user
        if(aliceUser!=null){
            return "登录成功";
        }else {
            return "密码不对！请检查密码！";
        }

    }
}
