package com.example.demo.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CTL
 * <p>excel方便调用的工具类 </p>
 * 就是根据获取到上传文件全名的路径来判断是不是excel文件，主要是根据后缀名
 * 创建日期：2020-10-30 10:03
 */
public class ExcelParserUtil {
    private static Logger logger = LoggerFactory.getLogger(ExcelParserUtil.class);
    /**
     * 是否是2003的excel，返回true 是2003
     * @param filePath
     * @return
     */
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }
    /**
     * 是否是2007的excel，返回true 是2007
     * @param filePath
     * @return
     */
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
    /**
     * 验证是否为EXCEL文件
     * @param filePath
     * @return
     */
    public static boolean validateExcel(String filePath){
        //没问题，我有问题，后面的括号范围之前看错了,那没事了
        if (filePath == null || !(   isExcel2003(filePath) || isExcel2007(filePath)  )){
            System.out.println("返回false"+false);
            return false;
        }
        return true;
    }
}
