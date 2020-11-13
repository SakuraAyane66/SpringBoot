package com.example.demo.admin.Jkyroad.service;

import com.example.demo.admin.Jkyroad.domain.JkyRoad;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RoadService {
    //批量操作的逻辑在readROadExcel中,所以mapper中有批量操作，但是这里并没有开发接口
//    //批量插入数据
//    int addRoadInformations(List<JkyRoad> roads);
//    //批量更新数据
//    int updateRoadInformations(List<JkyRoad> roads);
//    //批量删除数据
//    int deleteRoadInformation(List<JkyRoad> roads);
    //留出查询所有信息的接口
    List<JkyRoad> getRoads();

    //留出单条记录操作的接口
    //单独更新一条数据
    int updateRoadInformation(String roadId);
    //单独删除数据，前端界面的编辑
    int deleteRoadInformation(String roadId);
    //单独新增一条数据 ,增加到数据库表最后，然后展示的时候通过序号排序？？
    int addRoadInformation();

    //解析excel
    String parseExcel(MultipartFile file) throws Exception;
    //读取excel
    public List<JkyRoad> readRoadExcel(Workbook wb);
}
