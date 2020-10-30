package com.example.demo.update.service.impl;

import com.example.demo.common.utils.DateTransUtil;
import com.example.demo.common.utils.ExcelParserUtil;
import com.example.demo.common.utils.FileSaveUtil;
import com.example.demo.update.service.ExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

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
        if(fileName==null){
            return "文件名为空！请重新上传！";
        }
        //判断是否是excel，后缀名
        Boolean s = ExcelParserUtil.validateExcel(fileName);
        if(s==false){
            return "不是正确的excel文件！请上传excel文档！";
        }
        //判断文件内容是否为空,获取文件大小吧
        Long size = file.getSize();
        if(0==size){
            return "文件为空！请重新上传！";
        }
//        //将文件备份到服务器（接受并保存文件）
//        //文件路径
//        File fileDir = new File("D://文件保存类测试");
//        //如果文件路径不存在，新建文件路径
//        if(!fileDir.exists()){
//            fileDir.mkdirs();
//        }
//        //拼接保存文件的全名,前面是年月日小时分秒
//        File fileNew = new File(fileDir, DateTransUtil.getDateTime(System.currentTimeMillis())+ "--"+ fileName);
//        file.transferTo(fileNew);
//        //创建输入流
//        InputStream is = new FileInputStream(fileNew);
        //封装为方法
        boolean isSuccess= FileSaveUtil.fileSave(file,"D://文件保存类测试",fileName);
        if(isSuccess){
            return "文件上传成功！";
        }
        return "文件上传失败！";
    }
}
