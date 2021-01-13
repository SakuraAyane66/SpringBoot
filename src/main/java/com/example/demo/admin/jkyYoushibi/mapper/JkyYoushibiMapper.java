package com.example.demo.admin.jkyYoushibi.mapper;

import com.example.demo.admin.jkyYoushibi.domain.JkyYoushibi;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JkyYoushibiMapper {
    //获取jky_youshibi表中的所有数据
    List<JkyYoushibi> getAll();
}
