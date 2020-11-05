package com.example.demo.update.mapper;

import com.example.demo.update.domain.RoadInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExcelRoadInformationMapper {
    //批量插入
    public int addRoads(List<RoadInformation> roads);
    //List<RoadInformation>
    //批量删除
    public int deleteRoads(List<RoadInformation> roads);
    //批量更新
    public int updateRoads(List<RoadInformation> roads);

    //批量
}
