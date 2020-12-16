package com.example.demo.admin.pythontest.mapper;

import com.example.demo.admin.pythontest.domain.PythonGuanxi;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PythonTestMapper {
    //getAll，获取所有信息
    public List<PythonGuanxi> getAll();

    //通过主键id获取值
    public String getValueById(int id);
    //通过keyWord获取value（目前还没有设置keyword字段，未来预留的接口）
    public String getValueByKey(String key);

    //通过keywrod获取id，暂时也还没有keyWord字段,预留的接口
    public Integer getIdByKey(String key);


}
