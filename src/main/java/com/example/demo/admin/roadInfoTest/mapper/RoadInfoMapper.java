package com.example.demo.admin.roadInfoTest.mapper;

import com.example.demo.admin.roadInfoTest.domain.RoadInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoadInfoMapper {
    //获取所有的数据内容
    List<RoadInfo> getAll();
    //插入数据(单条记录)
    void addRoadInfo(RoadInfo roadInfo);
    //插入数据（批量插入，多条）
    void addRoadInfos(List<RoadInfo> roadInfos);
}
