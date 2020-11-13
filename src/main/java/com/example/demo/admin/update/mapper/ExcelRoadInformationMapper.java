package com.example.demo.admin.update.mapper;

import com.example.demo.admin.update.domain.RoadInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExcelRoadInformationMapper {
    //批量插入
    public int addRoads(List<RoadInformation> roads);
    //批量删除
    public int deleteRoads(List<RoadInformation> roads);
    //批量更新
    public int updateRoads(List<RoadInformation> roads);
    //批量查询 ，从数据库中查询返回具体的一个提交记录   //根据唯一标识的belong
    public List<RoadInformation> getRoads(int i); //i 是唯一标识belong
}
