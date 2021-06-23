package com.example.demo.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CTL
 * <p>封装一下保存上传文件的代码 </p>
 * <p>文件相关都在里面</p>
 * 创建日期：2020-10-30 15:08
 */
public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
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

    //首先还是解析json格式的文件（也可能是txt格式，但是是json数据）
    //注意这里读取的是一行的数据！（有的json文件存在txt中就是没有换行符）
    //后续优化说不定可以参考jky系统里面的那个代码
    public static JSONArray readFile(String filePath) {
        try {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                lineTxt = bufferedReader.readLine();
                JSONArray jsonArray = JSONObject.parseArray(lineTxt);
                read.close();
                return jsonArray;
            } else {
                logger.info("找不到指定的文件,路径:"+filePath);
                System.out.println("找不到指定的文件,路径:"+filePath);
            }
        } catch (Exception e) {
            System.out.println("读取"+filePath+"文件内容出错");
            e.printStackTrace();
        }
        return null;
    }
    //获取指定目录下所有的txt文件
    //使用（File还是不太熟悉啊..）
    // String FILE_IN = "D:\\工作相关\\二期项目\\数据txt文件\\customer";
    //        File f = new File(FILE_IN);
    //        List list = new ArrayList();
    //        list =getFileList(f);
    public static List getFileList(File file) {
        List result = new ArrayList();
        if (!file.isDirectory()){
            System.out.println(file.getAbsolutePath());
            result.add(file.getAbsolutePath());
        } else {
            File[] directoryList = file.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    if (file.isFile() && file.getName().indexOf("txt") > -1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            for (int i = 0; i < directoryList.length; i++) {
                result.add(directoryList[i].getPath());
            }
        }
        return result;
    }
}
