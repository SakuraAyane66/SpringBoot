package com.example.demo.service;

import com.example.demo.model.Student;

import java.util.List;

public interface StudentService {
    //获取所有学生
    List<Student> getAll();
    //添加学生
    void addStudent(Student student);
    //根据学生名字获取学生信息
    List<Student> getStudentByName(Student student);
    //根据dbn和Id查询学生信息,
    List<Student> getStudentDbnAndId(Student student);
}
