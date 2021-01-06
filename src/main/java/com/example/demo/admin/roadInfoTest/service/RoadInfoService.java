package com.example.demo.admin.roadInfoTest.service;

import com.example.demo.admin.roadInfoTest.domain.RoadInfo;

import java.util.List;

public interface RoadInfoService {
    //获取所有数据
    List<RoadInfo> getAll();
    //插入一条数据
    void addRoadInfo(RoadInfo roadInfo);
    //同时插入多条数据
    void addRoadInfos(List<RoadInfo> roadInfos);

}
