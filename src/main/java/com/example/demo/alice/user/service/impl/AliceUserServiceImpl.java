package com.example.demo.alice.user.service.impl;

import com.example.demo.alice.user.domain.AliceUser;
import com.example.demo.alice.user.mapper.AliceUserMapper;
import com.example.demo.alice.user.service.AliceUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    public void createUser(AliceUser user) {
        mapper.createUser(user);
    }
}
