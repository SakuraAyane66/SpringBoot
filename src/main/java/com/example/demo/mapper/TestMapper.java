package com.example.demo.mapper;

import com.example.demo.model.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//测试mapper
@Mapper
public interface TestMapper {

    //获取学生表所有的内容
    List<Student> getAll();
    //插入一条学生内容
    void addStudent(Student student);
    //根据学生名字查询学生信息，返回所有名字=xx人的信息
    List<Student> getStudentByName(Student student);

    //根据dbn和Id查询
    List<Student> getStudentDbnAndId(Student student);

}
