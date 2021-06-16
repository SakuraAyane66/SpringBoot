package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.model.StudentForm;
import com.example.demo.model.UserModel;
import com.example.demo.service.StudentInfoService;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CTL
 * <p>student控制器 </p>
 * 创建日期：2020-10-16 10:44
 */
@RestController
public class StudentController {
    //日志使用
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
    @Autowired
    private StudentInfoService studentInfoService;
    //必须加上注解才能加入到Ioc容器，然后让他统一管理
    @Autowired
    private UserService userService;
    @RequestMapping("/getAllStudent")
    public List<StudentForm> get(){
        List<StudentForm> list = studentInfoService.getAll();
        return list;
    }
    @RequestMapping("/getStudentById")
    public StudentForm getId(@RequestParam("id") int id){
        StudentForm student = studentInfoService.getById(id);
        return student;
    }

    //根据id找user，传入user找stu,查询成功！
    @RequestMapping("/getStuByUserId")
    public List<StudentForm> getStuByUserId(@RequestParam("id") int id){
        //传入对应的对象
//        System.out.println("????"+userService.get(id));
//        return null;
        UserModel user = userService.get(id);
        System.out.println("user是啥"+user);
        System.out.println("user的id是啥"+user.getId());
        List<StudentForm> students= studentInfoService.getStuByUserId(user);
        System.out.println("List是啥"+students);
        return students;
    }
}
