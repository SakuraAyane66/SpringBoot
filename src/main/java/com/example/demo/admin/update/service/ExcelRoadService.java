package com.example.demo.admin.update.service;

import com.example.demo.admin.update.domain.RoadInformation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExcelRoadService {

    String parseExcel(MultipartFile file) throws Exception;
    public List<RoadInformation> readRoadExcel(Workbook wb);

    //查询roadList信息
    public List<RoadInformation> getRoads(int id);
}
