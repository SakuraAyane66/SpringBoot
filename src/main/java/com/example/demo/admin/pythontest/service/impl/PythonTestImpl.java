package com.example.demo.admin.pythontest.service.impl;

import com.example.demo.admin.pythontest.domain.PythonGuanxi;
import com.example.demo.admin.pythontest.mapper.PythonTestMapper;
import com.example.demo.admin.pythontest.service.PythonTestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CTL
 * <p>读取python存进数据库的内容， java里service层实现 </p>
 * 创建日期：2020-12-16 09:33
 */
@Service
public class PythonTestImpl implements PythonTestService {

    @Resource
    private PythonTestMapper pythonTestMapper;

    @Override
    public String getValueByid(int id) {
        String x= pythonTestMapper.getValueById(id);
        return x;
    }

    @Override
    public List<PythonGuanxi> getAll() {
        return pythonTestMapper.getAll();
    }
}
