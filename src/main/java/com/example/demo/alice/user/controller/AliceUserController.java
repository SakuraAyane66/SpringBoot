package com.example.demo.alice.user.controller;

import com.example.demo.alice.user.domain.AliceUser;
import com.example.demo.alice.user.service.AliceUserService;
import com.example.demo.common.base.AjaxResult;
import com.example.demo.common.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.alibaba.druid.util.Utils.md5;

/**
 * @author CTL
 * <p>用户相关 </p>
 * 创建日期：2021-01-21 00:16
 */
@RestController
public class AliceUserController extends BaseController {
    @Autowired
    private AliceUserService userService;


    //注册新账号
    @ResponseBody
    @PostMapping("/createUser")
    public AjaxResult createUser(AliceUser user){
        if(user.getUsername()==null){
            return error("用户名为空,请输入用户名");
        }
        if(user.getPassword()==null){
            return error("密码为空,请输入用户名");
        }
        String msg =userService.createUser(user);
        return success(msg);
    }

    @PostMapping("/alice/login")
    public AjaxResult login(AliceUser user){
        System.out.println("user"+user);
        System.out.println("是否为空"+user.getPassword()=="");
        if(user.getUsername()==null||user.getUsername()==" "){
            return error("用户名为空,请输入用户名");
        }
        if(user.getPassword()==null || user.getPassword()==""){
            return error("密码为空,请输入用户名");
        }
       String msg = userService.login(user);
        return success(msg);
    }
}
