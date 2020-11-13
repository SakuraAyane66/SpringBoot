package com.example.demo.admin.Jkyroad.service.Impl;

import com.example.demo.admin.Jkyroad.domain.JkyRoad;
import com.example.demo.admin.Jkyroad.service.RoadService;
import com.example.demo.common.utils.DateTransUtil;
import com.example.demo.common.utils.ExcelParserUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @author CTL
 * <p>扩展 </p>
 * 创建日期：2020-11-13 15:45
 */
public class RoadServiceImpl implements RoadService {
    @Override
    public List<JkyRoad> getRoads() {
        return null;
    }

    @Override
    public int updateRoadInformation(String roadId) {
        return 0;
    }

    @Override
    public int deleteRoadInformation(String roadId) {
        return 0;
    }

    @Override
    public int addRoadInformation() {
        return 0;
    }

    //关键，解析excel表中数据
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
        //将文件备份到服务器（接受并保存文件）
        //文件路径
        File fileDir = new File("D://文件保存类测试");
        //如果文件路径不存在，新建文件路径
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        //拼接保存文件的全名,前面是年月日小时分秒
        //前面最好加该文件所属项目的名称
        File fileNew = new File(fileDir, DateTransUtil.getDateTime(System.currentTimeMillis())+ "--"+ fileName);
        file.transferTo(fileNew);
        //创建输入流
        InputStream is = new FileInputStream(fileNew);
        //解析excel流的workBook对象
        Workbook wb = null;
        //判断excel的版本
        if(ExcelParserUtil.isExcel2003(fileName)){
            wb = new HSSFWorkbook(is);
            System.out.println("excel版本是2003");
        }else {
            wb = new XSSFWorkbook(is);
            System.out.println("excel版本是2007");
        }
        return "文件上传成功！";
    }

    @Override
    public List<JkyRoad> readRoadExcel(Workbook wb) {
        return null;
    }
}
