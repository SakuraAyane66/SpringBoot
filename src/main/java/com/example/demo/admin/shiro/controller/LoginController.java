package com.example.demo.admin.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author CTL
 * <p>shiro登录认证和权限认证 </p>
 * 创建日期：2020-11-27 16:56
 */
@Controller
public class LoginController {
    private String prefix = "shiro/";
    @ResponseBody
    @RequestMapping("/login")
    public String hello(){
        return "测试login ok";
    }

    @RequestMapping("/shiro/test")
    public String test(Model model){
        model.addAttribute("CTL","Sakura Ayane");
        return prefix+"login";
    }

}
