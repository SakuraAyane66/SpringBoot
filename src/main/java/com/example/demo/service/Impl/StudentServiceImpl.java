package com.example.demo.service.Impl;

import com.example.demo.mapper.TestMapper;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CTL
 * <p>分表测试impl类 </p>
 * 创建日期：2021-06-11 15:46
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private TestMapper mapper; //获取mappe

    @Override
    public List<Student> getAll() {
        return mapper.getAll();
    }

    @Override
    public void addStudent(Student student) {
        mapper.addStudent(student);
    }
}
