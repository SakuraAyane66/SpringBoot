package com.example.demo.admin.update.controller;

import com.example.demo.common.base.AjaxResult;
import com.example.demo.common.base.BaseController;
import com.example.demo.admin.update.domain.RoadInformation;
import com.example.demo.admin.update.service.ExcelRoadService;
import com.example.demo.admin.update.service.ExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author CTL
 * <p>excel处理文件上传的接口 </p>
 * 创建日期：2020-10-30 10:09
 */
@RestController
public class ExcelController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(ExcelController.class);
    @Autowired
    private ExcelService excelService;  //测试导入
    @Autowired
    private ExcelRoadService excelRoadService; //road道路信息导入
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
    //road道路信息导入
    @RequestMapping(value = "/updatefile",method = RequestMethod.POST)
    public AjaxResult updatefile(MultipartFile file){
        try {
            String msg = excelRoadService.parseExcel(file);
            return success(msg);
        }catch (Exception ex){
            logger.info("文件解析出错",ex);
            return error("文件解析出错！");
        }
    }
    @RequestMapping("/getRoadInfor")
    public AjaxResult getRoadInfor(@RequestParam("id") int id){
        try{
            List<RoadInformation> list = excelRoadService.getRoads(id);
            return success("成功了！",list);
        }catch (Exception e){
            logger.info("文件信息获取出错了",e);
            return error("road信息获取出错");
        }
    }
}
