package com.example.demo.controller;

import com.example.demo.admin.update.domain.RoadInformation;
import com.example.demo.admin.update.domain.User;
import com.example.demo.common.base.AjaxResult;
import com.example.demo.common.base.BaseController;
import com.example.demo.common.utils.AllProjectTestUtil;
import com.example.demo.common.utils.UpUtil;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.weaver.loadtime.Aj;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <h3>demo</h3>
 * <p>User 控制层</p>
 *
 * @author : CTL
 * @date : 2020-10-14 14:33
 */
@RestController
//@RequestMapping("/testController")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;   //service层
//    @RequestMapping("/get.action")   //路径映射
//    //加上了session登录校验 ,已经没人用了
//    public UserModel get(@RequestParam("id") int id, HttpSession session){
//        UserModel user = (UserModel)session.getAttribute("user");
//        if(user==null){
//            return null;
//        }
//        UserModel userModel = userService.get(id);
//        return userModel;
//    }
   @RequestMapping("/get.action")   //路径映射
    public UserModel get(@RequestParam("id") int id){
        UserModel userModel = userService.get(id);
        return userModel;
    }
    //可行..
    @RequestMapping("/getEmail")
    public List<String> getEmail(@RequestParam("username") String username){
        List<String> list = userService.getEmail(username);
        return list;
    }
    @RequestMapping("/getUser")
    public List<UserModel> getUserByAge(@RequestParam("age") int age){
        List<UserModel> users = userService.getUserByAge(age);
        return users;
    }
    //获取所有的数据
    @RequestMapping("/getAll")
    public List<UserModel> getAll(){
        PageHelper.startPage(1,3);
        System.out.println("--------------到这里来了！");
        List<UserModel> users = userService.getAll();
        System.out.println("--------------到这里来了2！！");
        PageInfo<UserModel> page = new PageInfo<UserModel>(users);
        System.out.println("总数量：" + page.getTotal());
        System.out.println("当前页查询记录：" + page.getList().size());
        System.out.println("当前页码：" + page.getPageNum());
        System.out.println("每页显示数量：" + page.getPageSize());
        System.out.println("总页：" + page.getPages());
        return users;
    }
    //获取所有的数据
    @RequestMapping("/getAllPlus")
    public AjaxResult getAllTest(){
        PageHelper.startPage(3,3);
        List<UserModel> users = userService.getAll();
        PageInfo<UserModel> page = new PageInfo<UserModel>(users);
        System.out.println("总数量：" + page.getTotal());
        System.out.println("当前页查询记录：" + page.getList().size());
        System.out.println("当前页码：" + page.getPageNum());
        System.out.println("每页显示数量：" + page.getPageSize());
        System.out.println("总页：" + page.getPages());
        return success("成功了！！",page);
    }

    //获取name
    @RequestMapping("/getName")
    public String getName(@RequestParam("id") int id){
        String name = userService.getName(id);
        return name;
    }
    //Post方法，ajax请求，中间部分是获取接受的数据信息
    @PostMapping("/addUser")
    public String addUser(UserModel userModel){
        //UserModel userModel = new UserModel(id,name,age,email,address,username,password);//初始化一个实体类对象
        System.out.println("user看看"+userModel);
        //增加user
        userService.addUser(userModel);
        //尝试向全局的list中添加util
        AllProjectTestUtil.list.add(userModel);
        return "成功了";
    }
    //根据id删除user,可以执行
    @RequestMapping("/delete")
    public void delete(@RequestParam("id") int id){
        UserModel user = userService.get(id); //根据Id获取到user对象
        userService.deleteUser(user);  //调用方法删除userd
    }
    @PostMapping("/update")
    public void update(@RequestParam("id") int id,
                       @RequestParam("name") String name,
                       @RequestParam("age") int age,
                       @RequestParam("email") String email,
                       @RequestParam("address") String address,
                       @RequestParam("username") String username,
                       @RequestParam("password") String password){
       //后期写法，直接在参数获取哪儿得到user 模型，就能直接使用了,看上面UserModel模型
        UserModel user = new UserModel(id,name,age,email,address,username,password);  //根据传递的数据新建一个userMode对象
        userService.updateUser(user); //将对象注入到update方法中
    }

    //resultMap测试类
    @RequestMapping("/getUsersAndAuthor")
    public List<UserModel> getUsersAndAuthor(){
       List<UserModel> userModel = userService.getUsersAndAuthor();
       for (UserModel u:userModel){
           System.out.println(u);
       }
       AjaxResult json = success("成功!了！");
       System.out.println("json会是什么呢？"+json);
       return userModel;
    }

    @RequestMapping("/getUsersAndAuthor1")
    public AjaxResult getUsersAndAuthor1(){
        List<UserModel> userModel = userService.getUsersAndAuthor();
        for (UserModel u:userModel){
            System.out.println(u);
        }
        AjaxResult json = success("成功!了！",userModel);
        System.out.println("json会是什么呢？"+json);
        return json;
    }
    @RequestMapping("/getUserUpUtilTest")
    public List<User> getTest(){
       User u1 = new User();
       u1.setId(1);
       User u2 = new User();
       u2.setId(2);
       User u3 = new User();
       u3.setId(3);
       User u4 = new User();
       u4.setId(4);
       User u6 = new User();
       u6.setId(6);
       List<User> old = new ArrayList<>();//新增了u1,u2
       old.add(u1);
       old.add(u2);
       List<User> newarr = new ArrayList<>();
       newarr.add(u2);
       newarr.add(u3);

       List<User> result = UpUtil.diffList(newarr,old);
        System.out.println("result是"+result);
       return null;
    }
    @RequestMapping("/redistest")
    public Object setRedisTest(){
       boolean f = userService.setRedisTest();
        System.out.println(f);
       return null;
    }
    @RequestMapping("/getRedis")
    public Object getRedisTest(@RequestParam("name") String name){
        System.out.println("获取到的name是"+name);
        System.out.println("转化前的是"+userService.getRedisTest(name));
        UserModel user = userService.getRedisTest(name);
        System.out.println("从redis中取到user是什么"+user);
       return user;
    }
    //测试查询记录是多条，但是设置的时候返回实体而不是list
    @GetMapping("/getTest/Testdomian")
    public String getTestDomain(){
        UserModel userModel = userService.getTestAll(); //测试
        System.out.println(userModel);
        List<UserModel> list = new ArrayList<>();
        list.add(userModel);
        for(UserModel ll:list){
            System.out.println("list里面是"+ll);
        }
       return "success";
    }

    @GetMapping("/getTest/ThreadTest")
    public String getThreadTest() throws InterruptedException{
       userService.getThreadTest();
       return "success";
    }

    @GetMapping("/getTest/getTestMapperOrder")
    public String getTestMapperOrder(){

       userService.getTestMapperOrder();
       return "success";
    }

    @GetMapping("/getTest/GetAllProjectUsers")
    public List<UserModel> getAllProject(){
       for(UserModel ll:AllProjectTestUtil.list) {
           System.out.println("在get方法中获取到的"+ll);
       }
       return AllProjectTestUtil.list;
    }
}
