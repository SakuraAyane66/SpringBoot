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
        System.out.println("是否成功进入到了这里"+user);
        user.setClearPassword(user.getPassword());
        user.setPassword(md5(user.getClearPassword())); //对明文密码加密
        userService.createUser(user);
        return success("注册成功！");
    }


}
