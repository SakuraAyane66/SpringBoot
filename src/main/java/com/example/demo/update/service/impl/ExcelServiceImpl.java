package com.example.demo.update.service.impl;

import com.example.demo.update.service.ExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author CTL
 * <p>服务接口的实现 </p>
 * 创建日期：2020-10-30 09:55
 */
@Service
public class ExcelServiceImpl implements ExcelService {
    //日志功能
    private static Logger logger = LoggerFactory.getLogger(ExcelService.class);
//    @Override
//    public void downLoadExcel(HttpServletResponse response) {
//
//    }
//    @Override
//    public ResponseEntity fileUpload(MultipartFile file) {
//        return null;
//    }
    //重写接口的方法，这里是真实的业务逻辑
    @Override
    public String parseExcel(MultipartFile file) throws Exception {
        //调用函数获取上传文件名字.
        String fileName = file.getOriginalFilename();
        System.out.println("fileName----->"+fileName);
        return "success";
    }
}
