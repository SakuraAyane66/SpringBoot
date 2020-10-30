package com.example.demo.common.utils;

import com.example.demo.update.service.ExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author CTL
 * <p>封装一下保存上传文件的代码 </p>
 * 创建日期：2020-10-30 15:08
 */
public class FileSaveUtil {
    private static Logger logger = LoggerFactory.getLogger(FileSaveUtil.class);
    //传入文件,文件保存路径和传入文件的名字
    //这里还没进行文件的校验,在实际逻辑中完成的文件校验
    public static Boolean fileSave(MultipartFile file, String path, String fileName){
        //将文件备份到服务器（接受并保存文件）
        //文件路径
        File fileDir = new File(path);
        //如果文件路径不存在，新建文件路径
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        //拼接保存文件的全名,前面是年月日小时分秒
        File fileNew = new File(fileDir,DateTransUtil.getDateTime(System.currentTimeMillis())+ "--"+fileName);
        //对其进行捕获
       try {
           file.transferTo(fileNew);
           //创建输入流
           InputStream is = new FileInputStream(fileNew);
           //关闭
           is.close();
           return true;
       }catch (Exception e){
           logger.info("保存文件出错了！");
           System.out.println("保存文件出错了!");
           System.out.println(e);

       }
        return false;
    }
}
