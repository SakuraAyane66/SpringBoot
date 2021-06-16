package com.example.demo.service;

import com.example.demo.model.Student;

import java.util.List;

public interface StudentService {
    //获取所有学生
    List<Student> getAll();
    //添加学生
    void addStudent(Student student);
}
