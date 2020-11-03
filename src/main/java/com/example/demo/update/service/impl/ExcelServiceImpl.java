package com.example.demo.update.service.impl;

import com.example.demo.common.utils.DateTransUtil;
import com.example.demo.common.utils.ExcelParserUtil;
import com.example.demo.common.utils.FileSaveUtil;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserModel;
import com.example.demo.update.domain.User;
import com.example.demo.update.mapper.ExcelUserMapper;
import com.example.demo.update.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CTL
 * <p>服务接口的实现 </p>
 * 创建日期：2020-10-30 09:55
 */
@Service
public class ExcelServiceImpl implements ExcelService {
    //日志功能
    private static Logger logger = LoggerFactory.getLogger(ExcelService.class);
    //插入excelUserMapper，调用user插入数据
    @Resource
    private ExcelUserMapper mapper;
//    @Autowired
//    private UserMapper userMapper;

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
        //将文件备份到服务器（接受并保存文件）
        //文件路径
        File fileDir = new File("D://文件保存类测试");
        //如果文件路径不存在，新建文件路径
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        //拼接保存文件的全名,前面是年月日小时分秒
        File fileNew = new File(fileDir, DateTransUtil.getDateTime(System.currentTimeMillis())+ "--"+ fileName);
        file.transferTo(fileNew);
        //创建输入流
        InputStream is = new FileInputStream(fileNew);
        //此时还不能关闭输入流....
       // if(isSuccess){
//            return "文件上传成功！";
//        }
        //封装为方法
        //boolean isSuccess= FileSaveUtil.fileSave(file,"D://文件保存类测试",fileName);

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
        String errmsg = "错误信息！";

        //获取总表的数目（总sheets的数目）
        int totalSheets = wb.getNumberOfSheets();   //这个方法可以获取到sheets的总数目
        //System.out.println("totalSheets的数目为:"+totalSheets);
         List<User> list = readExcel(wb);
        //通过Sheet对象，新建的是Sheet id为0的对象 ,先测试
       // Sheet sheet0 = wb.getSheetAt(0);
        //System.out.println("sheet0是什么"+sheet0);
//        //行数,调用这个函数就能获取到它的实际行数，太方便了！！
//        int totalRows = sheet0.getPhysicalNumberOfRows();
//        System.out.println("行数为"+totalRows);
//        //列数，不能直接调用，要算？
//        int totalCells = 0;
//        //totalCells = sheet0.getRow(0).getPhysicalNumberOfCells(); //算的是第1行的cells参数为0
//        //第二行算起且不为空
//        if(totalRows >= 2 &&  null != sheet0.getRow(1)){
//            totalCells = sheet0.getRow(1).getPhysicalNumberOfCells();
//        }
//        System.out.println("totalCells是："+totalCells);
        //获取具体行
//        Row row = sheet0.getRow(0);
//        String xx=row.getCell(0).getStringCellValue();
//        System.out.println("cell里面的具体信息是:"+xx); //能够获取到信息！！！
        return "文件上传成功！";
    }

    //解析表单的方法
    @Transactional(rollbackFor = Exception.class) //有数据库操作，添加了事务
    public List<User> readExcel(Workbook wb){
        String errMsg="出现错误！请排查";
        //解析数据转为user对象
        List<User> list = new ArrayList<>();
        Sheet sheet0 = wb.getSheetAt(0);
        int count = 0; //返回数据库插入条数记录
        int totalRows = sheet0.getPhysicalNumberOfRows(); //总行数
        int totalCells = 0; //总列数
        //totalCells = sheet0.getRow(0).getPhysicalNumberOfCells(); //算的是第1行的cells参数为0
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
            Integer cell1 = (int)row.getCell(0).getNumericCellValue();//转为Interger
            String cell2 = row.getCell(1).getStringCellValue();
            int cell3 = (int)row.getCell(2).getNumericCellValue(); //转为int
            String cell4 = row.getCell(3).getStringCellValue();
            String cell5 = row.getCell(4).getStringCellValue();
            String cell6 = row.getCell(5).getStringCellValue();
            String cell7 = row.getCell(6).getStringCellValue();
            User user = new User(cell1,cell2,cell3,cell4,cell5,cell6,cell7);
            System.out.println("user是啥"+user);
            list.add(user);
            //循环的时候是在这里插入数据
            //int ss = mapper.addUser(user);
            //count+=ss;
            //用之前的usermodel 插入
            //UserModel userModel = new UserModel(cell1,cell2,cell3,cell4,cell5,cell6,cell7);
            //userMapper.addUser(userModel);
            System.out.println("List是"+list);
        }
        count = mapper.addUsers(list);
        System.out.println("总记录条数为"+count);
        return null;
    }
}
