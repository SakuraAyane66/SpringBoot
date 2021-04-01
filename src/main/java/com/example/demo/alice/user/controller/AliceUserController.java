package com.example.demo.alice.user.controller;

import com.example.demo.alice.user.domain.AliceUser;
import com.example.demo.alice.user.domain.OnlineUser;
import com.example.demo.alice.user.service.AliceUserService;
import com.example.demo.alice.user.service.OnlineUserService;
import com.example.demo.common.base.AjaxResult;
import com.example.demo.common.base.BaseController;
import com.example.demo.common.utils.IpUtil;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    @Autowired
    private OnlineUserService onlineUserService; //引入online/日志 服务

    @PostMapping("alice/test")
    @ResponseBody
    public String getIp(HttpServletRequest request,@RequestBody AliceUser user){
        //此工具类不能分辨是是否是谷歌浏览器设置的是iphone访问
        String firstip = request.getLocalAddr();
        System.out.println("firstIp"+firstip);
        String agent = request.getHeader("User-agent");
        System.out.println("agent是"+agent);
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);
//获取浏览器对象
        Browser browser = userAgent.getBrowser();
        String bname = browser.getName();
        System.out.println("browser :"+bname);
//获取操作系统对象
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        String sysname = operatingSystem.getName();
        System.out.println("Systerm:"+sysname);

        String ip = IpUtil.getIpAddr(request);
        return ip;
    }

    //注册新账号
    @ResponseBody
    @PostMapping("alice/createUser")
    public AjaxResult createUser(@RequestBody AliceUser user){
        //此处要加上用手机账号登录()
//        if(user.getPhonenumber()!=null){
//            //调用手机登录接口
//            //判断验证码是否一致
//            if( 验证码正确 xx){
//                userService.creatUserByPhoneNumber(user);
//                return success("");
//            }else {
//                return error("验证码错误，请稍后重试！");
//            }
//        }
        System.out.println("user是"+user);
        System.out.println("username:"+user.getUsername());
        System.out.println("password："+user.getClearPassword());
        System.out.println("rePassword:"+user.getReclearPassword());
        System.out.println("t or f ? :"+user.getReclearPassword()==user.getClearPassword());
        //网页注册
        if(user.getUsername()==null){
            return error("用户名为空,请输入用户名");
        }
        if(user.getClearPassword()==null){
            return error("密码为空,请输入用户名");
        }

        //两次密码不一样
        if(!user.getClearPassword().equals(user.getReclearPassword())){
            return error("两次密码不一样，请核对密码后重新输入！");
        }
        String msg =userService.createUser(user);
        return success(msg);
    }

    //还是把request处理的部分放到service中吧，c中只处理返回，逻辑还是在service中
    @PostMapping("/alice/login")
    public AjaxResult login(HttpServletRequest request,@RequestBody AliceUser user){
        //如果是手机登录，先赋值username
        if(user.getPhonenumber()!=null && user.getPhonenumber()!=""){
            user.setUsername(user.getPhonenumber()); //将phonenumber赋值给username（手机号注册的username就是手机号）
        }
        if(user.getUsername()==null||user.getUsername()==""){
            System.out.println("测试失败1！用户名为空");
            //此时向登录日志中插入失败信息
            onlineUserService.insertLoginInfor(request,user,"0","用户名为空");
            return error("用户名为空,请输入用户名");
        }
        if(user.getClearPassword()==null || user.getClearPassword()==""){
            System.out.println("测试失败2！密码为空");
            //此时向登录日志中插入失败信息
            onlineUserService.insertLoginInfor(request,user,"0","密码为空");
            return error("密码为空,请输入用户名");
        }
        //登录端，手机注册的和网页注册的都一个接口
        //此处通过验证resultMap的信息判断是否成功登录
        Map<String,String> resultMap = userService.login(user);
        //如果返回的map中包含了token，登录成功
        if(resultMap.get("token")!=null){
            return success(resultMap.get("msg"),resultMap);
        }else{
            //不包含token
            return error(resultMap.get("msg"));
        }
    }
}
