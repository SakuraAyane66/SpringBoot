package com.example.demo.mapper;

import com.example.demo.author.domain.Author;
import com.example.demo.model.StudentForm;
import com.example.demo.model.UserModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    //获取所有Student信息
    List<StudentForm> getAll();
    //根据id查询
    StudentForm getById(int id);
    //查询具体的国家
    String getCountryById(int id);
    //关联查询

    //根据用户添加用户的token
    void addToken(StudentForm studentForm);
    //根据token获取用户信息
    StudentForm getByToken(String token);

    //关联查找对象，根据id找到user对象，根据user对象的age查询对应student对象里面age相同的所有信息。
    List<StudentForm> getStuByUserId(UserModel userModel); //传入的是一个user实体类
}
