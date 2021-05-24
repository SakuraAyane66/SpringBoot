package com.example.demo.alice.publicconnect.service;

import com.example.demo.alice.publicconnect.domain.Publicconnect;

import java.util.List;

public interface PublicconnectService {
    //获取最新9条数据
    List<Publicconnect> getNine();
    //查询所有的公共记录
    List<Publicconnect> getAll();
    //根据id查询一个实体类
    Publicconnect getPublicconnect(Integer id);
    //根据String 关键词查询包含这个String的集合
    List<Publicconnect> getPublicconnectByString(String keyword);
}
