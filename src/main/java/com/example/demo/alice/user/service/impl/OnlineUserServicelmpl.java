package com.example.demo.alice.user.service.impl;

import com.example.demo.alice.user.domain.AliceUser;
import com.example.demo.alice.user.domain.OnlineUser;
import com.example.demo.alice.user.mapper.OnlineUserMapper;
import com.example.demo.alice.user.service.OnlineUserService;
import com.example.demo.common.utils.IpUtil;
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

    @Override
    public String insertOnlineUser(HttpServletRequest request, AliceUser user) {
        //此时的user只有username和明文密码（手机账号登录还没有username）
        String agent = request.getHeader("User-agent"); //得到请求头
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);
        //调用获取ip的控制器
        String ip = IpUtil.getIpAddr(request);
        //得到浏览器类型
        String browserName = userAgent.getBrowser().getName();
        //得到操作系统名字
        String os = userAgent.getOperatingSystem().getName();

        return null;
    }

    //向登录日志中插入一条信息
    @Override
    public String insertLoginInfor(HttpServletRequest request, AliceUser user, String status, String msg) {
        OnlineUser onlineUser = OnlineUserServicelmpl.analysisHttp(request);//取的解析后的实体
        onlineUser.setUsername(user.getUsername()); //如果用户名为空，这里会不会出错！*****
        onlineUser.setStatus(status); //赋值status
        onlineUser.setMsg(msg); //赋值msg
        mapper.insertLoginInfor(onlineUser);
        return null;
    }

    //返回一个包含了解析http信息之后的OnlineUser实体类
    public static OnlineUser analysisHttp(HttpServletRequest request){
        OnlineUser onlineUser = new OnlineUser();//新建实体
        String agent = request.getHeader("User-agent"); //得到请求头
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);
        //调用获取ip的控制器
        String ip = IpUtil.getIpAddr(request);
        onlineUser.setIpaddr(ip);//赋值ip
        //得到浏览器类型
        String browserName = userAgent.getBrowser().getName();
        onlineUser.setBrowser(browserName); //赋值browser名字
        //得到操作系统名字
        String os = userAgent.getOperatingSystem().getName();
        onlineUser.setOs(os);
        //至于login_time的问题考虑是在这里还是在mybatis中进行插入
        return onlineUser;
    }
}
