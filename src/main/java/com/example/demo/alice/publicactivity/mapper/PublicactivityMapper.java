package com.example.demo.alice.publicactivity.mapper;

import com.example.demo.alice.publicactivity.domain.Publicactivity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PublicactivityMapper {
    //获取最新9条数据
    List<Publicactivity> getNine();
    //查询所有的公共记录
    List<Publicactivity> getAll();
    //根据id查询一个实体类
    Publicactivity getPublicactivity(Integer id);
    //根据String 关键词查询包含这个String的集合
    List<Publicactivity> getPublicactivityByString(String keyword);
}
