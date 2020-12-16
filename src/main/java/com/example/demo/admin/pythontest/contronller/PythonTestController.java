package com.example.demo.admin.pythontest.contronller;

import com.example.demo.admin.pythontest.domain.PythonGuanxi;
import com.example.demo.admin.pythontest.service.PythonTestService;
import com.example.demo.common.base.AjaxResult;
import com.example.demo.common.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author CTL
 * <p>供前端调用的controller </p>
 * 创建日期：2020-12-16 09:34
 */
@RestController
public class PythonTestController extends BaseController {
    @Autowired
    private PythonTestService pythonTestService;

    //返回数据给前端，前端调用
    @RequestMapping("/python/getGuanxi")
    public AjaxResult test(){
        //作为前端需要的返回结果
        HashMap<String, String[]> result = new HashMap<>();
        List<PythonGuanxi> list = pythonTestService.getAll();
        list.forEach(str->{
            String arr[] = null;
            System.out.println(str.getKeyname());
            String temp = str.getValue(); //取的数据库value字符串的值
            temp = temp.substring(2,temp.length()-2);  //去掉中括号
            arr = temp.split("', '");
//            System.out.println(arr1);
           //String arr1[]={"马涛", "王真", "赵永利", "王宏畅", "廖公云", "薛国强", "李志栋", "岳学军", "薛彦卿"};
//            System.out.println("每一个str是什么");
//            System.out.println(str);
//            System.out.println(str.getId());
//            System.out.println(str.getKeyname());
//            System.out.println(str.getValue());
//            System.out.println(str.getValue() instanceof String);
             result.put(str.getKeyname(),arr);
        });
        return  success("",result);
    }

    //测试如何返回一个完整json格式给前端
    @RequestMapping("/python/test1")
    public AjaxResult test1(){
        HashMap<String,String[]> test = new HashMap<>();
        String arr[] = {"王者","无敌"};
        String arr1[] = {"测试","测试哟"};
//        test.put("王小明","['王涛','马真']");
//        test.put("sakura","['AA','BB']");
        test.put("aha",arr1);
        test.put("wao",arr);
        return success("kuaile",test);
    }
}
