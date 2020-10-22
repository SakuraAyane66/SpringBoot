package com.example.demo.config;

import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.UserContext;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author CTL
 * <p>拦截器  </p>
 * 创建日期：2020-10-21 10:18
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //目前关闭掉全局的token验证
        return true;

        //JavaWeb视频中从request中获取参数！！
//        String token = request.getParameter("token");
//        //简单的白名单，登录这个接口直接放行
//        if("/loginJwt".equals(request.getRequestURI())){
//            return true;
//        }
//        //通过token工具类解析携带的token内容
//        Claims claims = JwtUtil.parse(token);
//        //已登录的放行
//        if(claims!=null){
//            //将我们之前放到token中的username给存在上下文对象中
//            UserContext.add(claims.getSubject());
//            return true;
//        }
//        //走到这里代表其他接口，且没有登录
//        response.setContentType("application/json;charset=utf-8");
//        PrintWriter out = response.getWriter();
//        out.write("请先登录！");
//        out.flush();
//        out.close();
//        return false;
    }

    //删除掉Usercontext
    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,
                                Object handler,Exception ex) throws Exception
    {
        UserContext.remove();
        super.afterCompletion(request,response,handler,ex);
    }
}
