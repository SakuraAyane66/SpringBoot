package com.example.demo.alice.goodthings.service.Impl;

import com.example.demo.alice.goodthings.domain.Goodthings;
import com.example.demo.alice.goodthings.mapper.GoodthingsMapper;
import com.example.demo.alice.goodthings.service.GoodthingsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CTL
 * <p>好人好事服务层 </p>
 * 创建日期：2021-04-15 17:03
 */
@Service
public class GoodthinsService implements GoodthingsService {
    @Resource
    GoodthingsMapper mapper;

    @Override
    public List<Goodthings> getNine() {
        return mapper.getNine();
    }

    @Override
    public List<Goodthings> getAll() {
        return mapper.getAll();
    }

    @Override
    public Goodthings getGoodthingsByid(Integer id) {
        return mapper.getGoodthingsByid(id);
    }

    @Override
    public List<Goodthings> getGoodthingsByString(String keyword) {
        return mapper.getGoodthingsByString(keyword);
    }
}
