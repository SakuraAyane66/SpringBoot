package com.example.demo.update.service;

import com.example.demo.update.domain.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExcelRoadService {

    String parseExcel(MultipartFile file) throws Exception;
    public List<User> readExcel(Workbook wb);
}
