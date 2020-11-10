package com.example.demo.update.service.impl;

import com.example.demo.common.utils.DateTransUtil;
import com.example.demo.common.utils.ExcelParserUtil;
import com.example.demo.common.utils.UpUtil;
import com.example.demo.update.domain.RoadInformation;
import com.example.demo.update.domain.User;
import com.example.demo.update.mapper.ExcelRoadInformationMapper;
import com.example.demo.update.service.ExcelRoadService;
import com.example.demo.update.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CTL
 * <p>roadInformation接口的扩展实现 </p>
 * 创建日期：2020-11-05 17:19
 */
@Service
public class ExcelRoadServiceImpl implements ExcelRoadService {
    //日志功能
    private static Logger logger = LoggerFactory.getLogger(ExcelRoadService.class);
    @Resource
    private ExcelRoadInformationMapper mapper;
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

        //根据i查询记录
        List<RoadInformation> roadInformations =mapper.getRoads(1);


        //获取总表的数目（总sheets的数目）
        int totalSheets = wb.getNumberOfSheets();   //这个方法可以获取到sheets的总数目
        List<RoadInformation> list = readRoadExcel(wb);

        return "文件上传成功！";
    }

    @Override
    @Transactional(rollbackFor = Exception.class) //有数据库操作，添加了事务
    public List<RoadInformation> readRoadExcel(Workbook wb) {
        String errMsg="readExcel出现错误！请排查";
        //解析数据转为user对象
        List<RoadInformation> list = new ArrayList<>();  //list的服务器读取数组

        List<RoadInformation> list1 = new ArrayList<>(); //差集数组1
        List<RoadInformation> list2 = new ArrayList<>(); //差集数组2
        List<RoadInformation> list3 = new ArrayList<>(); //交集数组
        List<RoadInformation> list4 = new ArrayList<>(); //通过i读取的数组
        Sheet sheet0 = wb.getSheetAt(0);
        int count = 0; //返回数据库插入条数记录
        int totalRows = sheet0.getPhysicalNumberOfRows(); //总行数
        int totalCells = 0; //总列数
        //第二行算起且不为空
        if(totalRows >= 2 &&  null != sheet0.getRow(1)){
            totalCells = sheet0.getRow(1).getPhysicalNumberOfCells();
        }
        //从表的第二行开始，而不是从第一行开始，i=1而不是0
        for(int i=1,length=totalRows;i<length;i++){
            Row row = sheet0.getRow(i); //获取具体行
            if(null == row){
                logger.info("===========>第"+i+"行数据有问题，请核查数据！");
            }
            int cell1;
            //开始行，第二行，序号为开始，变为0
            if(i==1){
                 cell1 = 0;
            }else if(i==totalRows-1)
            {
                 cell1 = totalRows-2;
            }
            else{
                 cell1 = (int)row.getCell(0).getNumericCellValue();//转为Interger，获取order_number
            }
            String cell2 = row.getCell(1).getStringCellValue();//获取桩号
            String cell3 = row.getCell(2).getStringCellValue(); //获取类型
            double cell4 = (double) row.getCell(3).getNumericCellValue(); //转为double x坐标
            double cell5 = (double) row.getCell(4).getNumericCellValue();//y坐标
            double cell6 = (double) row.getCell(5).getNumericCellValue(); //lat坐标
            double cell7 = (double) row.getCell(6).getNumericCellValue(); //lon坐标
            String cell8 = row.getCell(7).getStringCellValue();//获取方法,填方or挖方
            double cell9 = (double) row.getCell(8).getNumericCellValue(); //面层 宽度
            double cell10 = (double) row.getCell(9).getNumericCellValue();//上基层
            double cell11 = (double) row.getCell(10).getNumericCellValue();//下基层
            double cell12 = (double) row.getCell(11).getNumericCellValue();//底基层
            //最后一位int belong现在添加的
            RoadInformation roadInformation = new RoadInformation(cell1,cell2,cell4,cell5,cell3,cell6,
                    cell7,cell8,cell9,cell10,cell11,cell12,1);
            //将user添加进批量操作的list里
            list.add(roadInformation);
            //System.out.println("List是"+list);
        }
        System.out.println("====================");
        list4 = mapper.getRoads(1); //读取数组
        list3 = UpUtil.sameRoadList(list,list4); //读取交集
        if(!list3.isEmpty()){
            mapper.addRoads(list3);
        }
        mapper.addRoads(list);


        return null;
    }
}
