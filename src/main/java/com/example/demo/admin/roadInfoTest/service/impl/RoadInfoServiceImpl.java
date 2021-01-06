package com.example.demo.admin.roadInfoTest.service.impl;

import com.example.demo.admin.roadInfoTest.domain.RoadInfo;
import com.example.demo.admin.roadInfoTest.mapper.RoadInfoMapper;
import com.example.demo.admin.roadInfoTest.service.RoadInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CTL
 * <p>service </p>
 * 创建日期：2020-12-30 22:53
 */
@Service
public class RoadInfoServiceImpl implements RoadInfoService{
    @Resource
    private RoadInfoMapper mapper;
    @Override
    public List<RoadInfo> getAll() {
        return mapper.getAll();
    }

    @Override
    public void addRoadInfo(RoadInfo roadInfo) {
        //插入roadInfo
        mapper.addRoadInfo(roadInfo);
    }

    @Override
    public void addRoadInfos(List<RoadInfo> roadInfos) {
        //插入roadinfos
        mapper.addRoadInfos(roadInfos);
    }
}
