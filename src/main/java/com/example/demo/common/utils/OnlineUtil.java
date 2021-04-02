package com.example.demo.common.utils;

import com.example.demo.DemoApplication;
import com.example.demo.alice.user.domain.OnlineUser;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CTL
 * <p>解析http请求，返回包含ip,浏览器名字和系统的online实体类 </p>
 * 创建日期：2021-04-01 16:41
 */
public class OnlineUtil {
    private static final Logger logger = LoggerFactory.getLogger(OnlineUtil.class);

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
