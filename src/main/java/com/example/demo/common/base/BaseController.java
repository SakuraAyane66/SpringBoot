package com.example.demo.common.base;

import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.formula.functions.T;
import org.aspectj.weaver.loadtime.Aj;

/**
 * @author CTL
 * <p>控制器的基类，有一些通用属性和方法 </p>
 * 创建日期：2020-10-27 09:50
 */
public class BaseController{
    //预处理先不管，只管返回

    //调用AjaxResult，不能继承它，是调用
    //返回成功信息，重载方法
    public AjaxResult success(String msg,Object data){
        return AjaxResult.success(msg,data);
    }
    public AjaxResult success(String msg){
        return AjaxResult.success(msg);
    }
    //与pageHelper联合使用，返回的ajax写到pageInfo信息
    public AjaxResult success(String msg, PageInfo<T> pageInfo){
        return AjaxResult.success(msg, pageInfo);
    }

    //返回错误消息 ，重载方法
    public AjaxResult error(int code,String msg){
        return AjaxResult.error(code,msg);
    }
    public AjaxResult error(String msg){
        return AjaxResult.error(msg);
    }
    public AjaxResult error(int code,String msg,Object data){
        return AjaxResult.error(code,msg,data);
    }
}
