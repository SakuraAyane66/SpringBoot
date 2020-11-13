package com.example.demo.admin.Jkyroad.mapper;

import com.example.demo.admin.Jkyroad.domain.JkyRoad;

import java.util.List;

public interface JkyRoadMapper {
    //查询所有的road记录
    List<JkyRoad> getRoads(String belong);

    //批量插入数据
    int addRoadInformations(List<JkyRoad> roads);
    //批量更新数据
    int updateRoadInformations(List<JkyRoad> roads);
    //批量删除数据
    int deleteRoadInformation(List<JkyRoad> roads);



    //单独更新一条数据
//    int updateRoadInformation(String roadId);
    int updateRoadInformation(JkyRoad road);
    //单独删除数据，前端界面的编辑
//    int deleteRoadInformation(String roadId);
    int deleteRoadInformation(String roadId);
    //单独新增一条数据 ,增加到数据库表最后，然后展示的时候通过序号排序？？
    int addRoadInformation();
}
