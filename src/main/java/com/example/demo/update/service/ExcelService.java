package com.example.demo.update.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * excel文件的导入导出测试接口
 */
public interface ExcelService{
//    /**
//     * 上传/导入
//     * @param file
//     * @return
//     */
//    ResponseEntity fileUpload(MultipartFile file);
//
//    /**
//     * 下载/导出
//     * @param response
//     */
//    void downLoadExcel(HttpServletResponse response);
    /**
     * 解析Excel文件
     * @param file
     * @return
     * @throws Exception
     */
    String parseExcel(MultipartFile file) throws Exception;
}
