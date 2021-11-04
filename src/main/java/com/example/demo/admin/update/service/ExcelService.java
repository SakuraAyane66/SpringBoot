package com.example.demo.admin.update.service;

import com.example.demo.admin.update.domain.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public List<User> readExcel(Workbook wb);
    public List<User> getUsers(int i);

    public void changeTest();
}
