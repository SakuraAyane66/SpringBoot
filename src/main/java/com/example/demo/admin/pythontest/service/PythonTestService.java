package com.example.demo.admin.pythontest.service;

import com.example.demo.admin.pythontest.domain.PythonGuanxi;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PythonTestService {
    //根据id获取value
    public String getValueByid(int id);

    //获取所有内容
    public List<PythonGuanxi> getAll();
}
