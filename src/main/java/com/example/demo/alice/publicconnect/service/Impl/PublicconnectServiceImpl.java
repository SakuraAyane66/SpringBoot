package com.example.demo.alice.publicconnect.service.Impl;

import com.example.demo.alice.publicconnect.domain.Publicconnect;
import com.example.demo.alice.publicconnect.mapper.PublicconnectMapper;
import com.example.demo.alice.publicconnect.service.PublicconnectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CTL
 * <p>公益链接service类 </p>
 * 创建日期：2021-05-21 16:30
 */
@Service
public class PublicconnectServiceImpl implements PublicconnectService {
    @Resource
    private PublicconnectMapper mapper;
    @Override
    public List<Publicconnect> getNine() {
        return mapper.getNine();
    }

    @Override
    public List<Publicconnect> getAll() {
        return mapper.getAll();
    }

    @Override
    public Publicconnect getPublicconnect(Integer id) {
        return mapper.getPublicconnect(id);
    }

    @Override
    public List<Publicconnect> getPublicconnectByString(String keyword) {
        return mapper.getPublicconnectByString(keyword);
    }
}
