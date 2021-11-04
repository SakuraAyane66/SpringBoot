package com.example.demo.admin.update.service.impl;

import com.example.demo.admin.update.service.ExcelService;
import com.example.demo.common.utils.DateTransUtil;
import com.example.demo.common.utils.ExcelParserUtil;
import com.example.demo.common.utils.FileUtil;
import com.example.demo.common.utils.UpUtil;
import com.example.demo.admin.update.domain.User;
import com.example.demo.admin.update.mapper.ExcelUserMapper;
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

import static com.example.demo.common.utils.FileUtil.getFileList;

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
        //前面最好加该文件所属项目的名称
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
    @Override
    @Transactional(rollbackFor = Exception.class) //有数据库操作，添加了事务
    public List<User> readExcel(Workbook wb){
        String errMsg="readExcel出现错误！请排查";
        //解析数据转为user对象
        List<User> list = new ArrayList<>();
//        List<User> list1 = new ArrayList<>(); //批量更新数组
//        List<User> list2 = new ArrayList<>(); //批量插入数组
        List<User> list1 = new ArrayList<>(); //差集数组1
        List<User> list2 = new ArrayList<>(); //差集数组2
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
            int cell8 = (int)row.getCell(7).getNumericCellValue(); //转为int
            User user = new User(cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8);
            //将user添加进批量操作的list里
            list.add(user);
            System.out.println("List是"+list);
        }

        //循环遍历list，进行判断主键是否存在 ，也不需要这种写法了
//        for(User item:list){
//            System.out.println("item是"+item);
//            int is= mapper.isExist(item);
//            System.out.println(item+"的is是"+is);
//            if(is>0){
//                list1.add(item);
//            }else {
//                list2.add(item);
//            }
//        }
//        System.out.println("list1"+list1);
//        System.out.println("===========================================");
//        System.out.println("list2"+list2);
//        System.out.println(!list1.isEmpty());
//        if(!list1.isEmpty()){
//           count = mapper.updateUsers(list1);
//        }
//        if(!list2.isEmpty()){
//            mapper.addUsers(list2);
//        }
        System.out.println("====================");
        //获取数据库中存在的集合，根据belong所属项目，实际就是通过文件生成的uuid或者说是用户自定义的唯一文件判断标识
        //这个可以通过服务器端解析获取，里面的参数
        List<User> ll=mapper.getUsers(1);
        System.out.println("数据库中存在的users："+ll);
        System.out.println("====================");
        //取交集，非空的时候交集部分执行更新操作
        if(!UpUtil.sameList(ll,list).isEmpty()){
            mapper.updateUsers(UpUtil.sameList(ll,list)); //
        }
        list1 = UpUtil.diffList(list,ll);
        System.out.println("list1的差集为"+list1);  //list1是需要新增的，参数先导入的list就是需要增加的
        list2 = UpUtil.diffList(ll,list);
        System.out.println("list2的差集为"+list2);  //list2是需要删除的
        mapper.addUsers(list1);
        mapper.deleteUsers(list2);
        System.out.println("总记录条数为"+count);
        return null;
    }

    @Override
    public List<User> getUsers(int i){
        return null;
    }

    @Override
    public void changeTest() {
        String FILE_IN = "D:\\工作相关\\二期项目\\数据txt文件\\customer";
                File f = new File(FILE_IN);
                List list = new ArrayList();
                list =getFileList(f);
        List<String> stringList = new ArrayList<>();
        stringList.add("xxx");
        return;
//        for(String ss:stringList){
//            Boolean s = ExcelParserUtil.validateExcel(ss);
//            if(s==false) {
//                return;
//            }
//            //解析excel流的workBook对象
//            Workbook wb = null;
//            //判断excel的版本
//            if(ExcelParserUtil.isExcel2003(ss)){
//                wb = new HSSFWorkbook();
//                System.out.println("excel版本是2003");
//            }else {
////                wb = new XSSFWorkbook(is);
//                System.out.println("excel版本是2007");
//            }
//        }
    }
}
