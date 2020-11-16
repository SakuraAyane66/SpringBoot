package com.example.demo.common;

import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import com.example.demo.common.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author CTL
 * <p>session实现登录校验 </p>
 * <p>JWT实现登录校验</p>
 * 创建日期：2020-10-20 10:48
 */
@RestController
public class SessionController {
    //引入userservice控制层
    @Autowired
    UserService userService;
    @PostMapping("/login") //post方法,因为是session校验，返回object的复杂情况在jwt讨论
    public String login(@RequestParam("username") String username,@RequestParam("password") String password,
                        HttpSession session
                        ){
        UserModel user = userService.findUser(username,password); //返回user实体
        //Object k = new Object();
        //判断是否有这个用户
        if(user!=null){
            session.setAttribute("user",user);
            return "登录成功!";
        }
        return "登录失败！";
    }

    //JWT 登录接口
    //JWT 的单次请求测试接口在HelloController /hello中
    //后续已经开启了全局的过滤器在config中的LoginInterceptor中，并且在启动类实现了接口的继承
    @PostMapping("/loginJwt")
    public String loginJwt(@RequestParam("username") String username,@RequestParam("password") String password){
        UserModel user = userService.findUser(username,password); //找到user
        if(user!=null){
            //传入user实体类的username
            return JwtUtil.generate(user.getUsername());
        }
        return "账号或者密码错误！";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "退出成功！";
    }
}
