package com.example.demo.update.service.impl;

import com.example.demo.update.domain.User;
import com.example.demo.update.service.ExcelRoadService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author CTL
 * <p>roadInformation接口的扩展实现 </p>
 * 创建日期：2020-11-05 17:19
 */
public class ExcelRoadServiceImpl implements ExcelRoadService {
    @Override
    public String parseExcel(MultipartFile file) throws Exception {
        return null;
    }

    @Override
    public List<User> readExcel(Workbook wb) {
        return null;
    }
}
