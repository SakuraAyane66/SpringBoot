package com.example.demo.alice.goodthings.service;

import com.example.demo.alice.goodthings.domain.Goodthings;

import java.util.List;

public interface GoodthingsService {
    //获取最新9条数据
    List<Goodthings> getNine();
    //查询所有的公共记录
    List<Goodthings> getAll();
    //根据id查询一个实体类
    Goodthings getGoodthingsByid(Integer id);
    //根据String 关键词查询包含这个String的集合
    List<Goodthings> getGoodthingsByString(String keyword);
}
