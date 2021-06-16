package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.alice.user.domain.OnlineUser;
import com.example.demo.common.utils.JwtUtil;
import com.example.demo.common.utils.OnlineUtil;
import com.example.demo.model.UserModel;
import com.example.demo.model.WZRGStation;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author CTL
 * <p>测试hello </p>
 * 创建日期：2020-10-14 00:32
 */
@Controller
public class HelloController {
    //日志使用
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    //测试Druid
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserService userService;

    //主页sakura
    @RequestMapping("/")
    public String nice(){
        return "index";
    }

    //python 数据展示页面
    @RequestMapping("/Python/test0")
    public String pythontest0(){
        return "PythonData";
    }

    //解析txt文本中的data并返回，
    @RequestMapping(value = "/Python/getTest0Data",method = RequestMethod.POST)
    @ResponseBody()
    public String getData(@RequestParam("filename") String filename){
        String pro = "D:\\";
        String filepath = pro+filename;
        System.out.println("filepath为"+filepath);
        return null;
    }

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request,@RequestParam("token") String token){
        //从请求头中获取jwt
        //String jwt = request.getHeader("Authorization");
        //改为了ajax中data携带token
        String jwt = token;
        //解析jwt
        if (JwtUtil.parse(jwt) == null) {
            return "请先登录";
        }
        //执行业务逻辑，解析完成之后
        Date nowtime = new Date(); //返回的是当前时间
        System.out.println("nowtime is "+nowtime);
        return "Hello sakura!";
    }
    //测试druid
    @RequestMapping("/test1")
    public String test1(){
        System.out.println(dataSource);
        return "test1";
    }
    //测试pathVaribale注解，解析路径中的{}
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") int userId){
        System.out.println("映射的userID为"+userId);
        return "sakura";
    }
    //测试模版引擎的接口文件
    //要将数据传递给模版引擎
    @RequestMapping("/thyme.html")
    public String index(Model model){
        UserModel user = userService.get(2);
//        user.setName("sakura");
//        user.setAddress("Tokyo");
//        user.setAge(22);
        model.addAttribute("user",user);
        return "test2";
    }
    @RequestMapping("/excel.html")
    public String App(){
        return "PostTest";
    }

    //不同的url映射到同一个thymeleaf页面呢？试一试
    @RequestMapping("/ssr.html")
    public String wt(ModelMap model){
        List<UserModel> users = new ArrayList<>();
        UserModel user1 = userService.get(1);
        UserModel user2 = userService.get(2);
        UserModel user3 = userService.get(3);
        users.add(user1);
        users.add(user2);
        users.add(user3);
//        model.addAttribute("users",users);
        model.put("users",users);
        return "test3";
    }

    //DTU调试的接口
    @PostMapping("/PostDTU")
    @ResponseBody()
    public String PostDtu(HttpServletRequest request, String model){
        OnlineUser user = OnlineUtil.analysisHttp(request);
        System.out.println("ip地址是"+user.getIpaddr());
        System.out.println("接收到的model是"+model);
        return "alice+"+model;
    }
    //DTU调试接口
    @GetMapping("/GetDTU")
    @ResponseBody()
    public String GetDtu(){
        return "Asuna";
    }
}
