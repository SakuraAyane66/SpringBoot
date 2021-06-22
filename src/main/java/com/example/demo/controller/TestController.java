package com.example.demo.controller;

import com.example.demo.common.base.AjaxResult;
import com.example.demo.common.base.BaseController;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CTL
 * <p>各种测试使用的controller </p>
 * 创建日期：2021-06-11 15:08
 */
//@Controller
//@ResponseBody
@RestController
public class TestController extends BaseController {
    @Autowired
    private StudentService studentService;


    @PostMapping("/Test/addStudent")
    public AjaxResult addStudent(@RequestBody Student student){
        System.out.println("Student是"+student);
        studentService.addStudent(student);
        return success("成功插入");
    }

    @GetMapping("/Test/getStudentAll")
    public AjaxResult getStuAll(){
        List<Student> list= studentService.getAll();
        return success("200",list);
    }

    @PostMapping("/Test/getStudentByName")
    public AjaxResult getStudentByName(@RequestBody Student student){
        List<Student> list = studentService.getStudentByName(student);
        return success("200",list);
    }
    //根据dbn查询 相关信息
    @PostMapping("/Test/getStudentDbnAndId")
    public AjaxResult getStudentDbnAndId(@RequestBody Student student){
        System.out.println("student?"+student);
        List<Student> list = studentService.getStudentDbnAndId(student);
        return success("200",list);
    }
}
