package com.example.demo.common.base;

import com.example.demo.model.UserModel;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.api.scripting.AbstractJSObject;
import org.apache.poi.ss.formula.functions.T;
import org.aspectj.weaver.loadtime.Aj;

import java.util.HashMap;

/**
 * @author CTL
 * <p>AJAX全局通用返回类 </p>
 * 创建日期：2020-10-27 09:51
 */
//HashMap是<K,V> 键值对 ，这里键为String类型，V为object类型
public class AjaxResult extends HashMap<String,Object> {
    //序列化
    private static final long seriaVersionUID = 1L;
    //初始化一个对象，无参构造器
    public AjaxResult() {
    }
    //返回错误信息
   public static AjaxResult error(int code,String msg){
        AjaxResult json = new AjaxResult();
        json.put("code",code);
        json.put("msg",msg);
        return json;
   }
   //重载,有错误信息data的时候
   public static AjaxResult error(int code,String msg,Object data){
        AjaxResult json = new AjaxResult();
        json.put("code",code);
        json.put("msg",msg);
        json.put("data",data);
        return json;
   }
   //重载，一个参数,返回调用2个参数的函数，写得好啊
    public static AjaxResult error(String msg){
         return error(500,msg);
    }
    //返回正确的信息
    public static AjaxResult success(String msg,Object data){
        AjaxResult json = new AjaxResult();
        json.put("code",200);
        json.put("msg",msg);
        json.put("data",data);
        return json;
    }
    //返回正确信息
    public static AjaxResult success(String msg){
        AjaxResult json = new AjaxResult();
        json.put("code",200);
        json.put("msg",msg);
        json.put("data",null);
        return json;
    }
    //返回正确信息，但是这里是和pageInfo联合使用。。。。
    //使用失败了，在c层返回的是上一个success返回的object对象了，虽然能返回，但是...有点问题啊....
    public static AjaxResult success(String msg, PageInfo page){
        AjaxResult json = new AjaxResult();
        json.put("code",200);
        json.put("msg",msg);
        json.put("pageInfo",page);
        return json;
    }
    //重写父类的put方法，暂时先不用管原理，会用就行
    @Override
    public AjaxResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
