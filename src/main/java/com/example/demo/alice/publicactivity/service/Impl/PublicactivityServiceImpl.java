package com.example.demo.alice.publicactivity.service.Impl;

import com.example.demo.alice.publicactivity.domain.Publicactivity;
import com.example.demo.alice.publicactivity.mapper.PublicactivityMapper;
import com.example.demo.alice.publicactivity.service.PublicactivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CTL
 * <p>公益活动的service类 </p>
 * 创建日期：2021-05-21 16:19
 */
@Service
public class PublicactivityServiceImpl implements PublicactivityService {
    @Resource
    private PublicactivityMapper mapper;
    @Override
    public List<Publicactivity> getNine() {
        return mapper.getNine();
    }

    @Override
    public List<Publicactivity> getAll() {
        return mapper.getAll();
    }

    @Override
    public Publicactivity getPublicactivity(Integer id) {
        return mapper.getPublicactivity(id);
    }

    @Override
    public List<Publicactivity> getPublicactivityByString(String keyword) {
        return mapper.getPublicactivityByString(keyword);
    }
}
