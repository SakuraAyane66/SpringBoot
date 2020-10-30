package com.example.demo.update.controller;

import com.example.demo.common.base.AjaxResult;
import com.example.demo.common.base.BaseController;
import com.example.demo.update.service.ExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CTL
 * <p>excel处理文件上传的接口 </p>
 * 创建日期：2020-10-30 10:09
 */
@RestController
public class ExcelController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(ExcelController.class);
    @Autowired
    private ExcelService excelService;
    @RequestMapping(value = "/parseExcel",method = RequestMethod.POST)
    public AjaxResult parseExcel(MultipartFile file){
        try {
            String msg = excelService.parseExcel(file);
            return success(msg);
        }catch (Exception ex){
            logger.info("文件解析出错",ex);
            return error("文件解析出错！");
        }
    }
}