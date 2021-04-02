package com.example.demo.alice.user.service.impl;

import com.example.demo.alice.user.domain.AliceUser;
import com.example.demo.alice.user.domain.OnlineUser;
import com.example.demo.alice.user.mapper.OnlineUserMapper;
import com.example.demo.alice.user.service.OnlineUserService;
import com.example.demo.common.utils.IpUtil;
import com.example.demo.common.utils.OnlineUtil;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author CTL
 * <p>在线用户操作服务 </p>
 * 创建日期：2021-03-31 23:25
 */
@Service
public class OnlineUserServicelmpl implements OnlineUserService {
    @Resource
    private OnlineUserMapper mapper;

    //向在线用户表插入一条数据
    @Override
    public int insertOnlineUser(HttpServletRequest request, OnlineUser onlineUser) {
        OnlineUser needOnlineUser = OnlineUtil.analysisHttp(request);//生成实体类，已经解析了ip,browser,os
        needOnlineUser.setStatus("1"); //1表示登录，0表示失败（正在登录表肯定就是1了）.
        int x= mapper.insertOnlineUser(needOnlineUser);

        return x;
    }

    //向登录日志中插入一条信息
    @Override
    public String insertLoginInfor(HttpServletRequest request, AliceUser user, String status, String msg) {
        OnlineUser onlineUser = OnlineUtil.analysisHttp(request);//取的解析后的实体
        onlineUser.setUsername(user.getUsername()); //如果用户名为空，这里会不会出错！*****
        onlineUser.setStatus(status); //赋值status
        onlineUser.setMsg(msg); //赋值msg
        mapper.insertLoginInfor(onlineUser);
        return null;
    }


}
