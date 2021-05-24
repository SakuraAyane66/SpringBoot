package com.example.demo.alice.publicactivity.service;


import com.example.demo.alice.publicactivity.domain.Publicactivity;

import java.util.List;
//公益活动service类
public interface PublicactivityService {
    //获取最新9条数据
    List<Publicactivity> getNine();
    //查询所有的公共记录
    List<Publicactivity> getAll();
    //根据id查询一个实体类
    Publicactivity getPublicactivity(Integer id);
    //根据String 关键词查询包含这个String的集合
    List<Publicactivity> getPublicactivityByString(String keyword);
}
