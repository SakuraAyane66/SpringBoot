package com.example.demo.service;

import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.StudentForm;
import com.example.demo.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CTL
 * <p>Student服务层 </p>
 * 创建日期：2020-10-16 10:41
 */
@Service
public class StudentInfoService {
    @Resource
    private StudentMapper studentMapper;

    public List<StudentForm> getAll(){
        List<StudentForm> list = studentMapper.getAll();
        return list;
    }
    public StudentForm getById(int id){
        StudentForm studentForm = studentMapper.getById(id);
        return studentForm;
    }
    public String getCountryById(int id){
            String country = studentMapper.getCountryById(id);
            return country;
    }
    //根据StudentForm对象，这个对象在controller层获取，添加用户的token
    public void addToken(StudentForm studentForm){
        studentMapper.addToken(studentForm); //调用方法
    }
    public StudentForm getByToken(String token){
        StudentForm studentForm = studentMapper.getByToken(token);
        return studentForm;
    }
    //关联连表查询,根据id查询user，然后传入user实体对象，根据user的age找到age的所有对象
    //传入user实体对象
    public List<StudentForm> getStuByUserId(UserModel userModel){
       List<StudentForm>  students = studentMapper.getStuByUserId(userModel);
        return students;
    }
}
