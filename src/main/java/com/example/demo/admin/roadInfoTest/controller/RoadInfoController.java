package com.example.demo.admin.roadInfoTest.controller;

import com.example.demo.admin.roadInfoTest.domain.RoadInfo;
import com.example.demo.admin.roadInfoTest.service.RoadInfoService;
import com.example.demo.common.base.AjaxResult;
import com.example.demo.common.base.BaseController;
//import org.hibernate.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CTL
 * <p>controller </p>
 * 创建日期：2020-12-30 22:51
 */
@RestController  //不用返回视图，只返回数据
public class RoadInfoController extends BaseController {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(RoadInfoController.class);
    @Autowired
    private RoadInfoService roadInfoService;

    //返回所有记录
    @GetMapping("/roadInfo/Test0")
    public List<RoadInfo> test0(){
        try {
            //暂停5s
          Thread.sleep(5000);
          return roadInfoService.getAll();
        }catch (Exception e){
            return null;
        }
    }

    //前端只传递一个number（类比roadId给后端）
    @GetMapping("/roadInfo/Test1")
    public AjaxResult test1(RoadInfo roadInfo) {
        //逻辑放到service层好一点
        RoadInfo domain = new RoadInfo(); //新建domain
        domain.setNumber(roadInfo.getNumber()); //
        try {
            String msg = "成功了";
            domain.setStatus("1");
            roadInfoService.addRoadInfo(domain);
            Thread.sleep(1000*3); //休眠3s，相当于计算用时花了3s
            return success(msg);
        }catch (Exception e){
            String msg = "失败";
            domain.setStatus("0");
            roadInfoService.addRoadInfo(domain);
            return error(msg);
        }
    }
    //接受多个参数，后端循环
    @GetMapping("/roadInfo/Test2")
    public AjaxResult test1(@RequestParam("data") String data){
        String[] list = data.split(","); //解析成string数组
//        System.out.println("数组list为"+list);
        String[] fanhui = new String[list.length]; //新建fanhui
         for(int i=0;i<list.length;i++){
               fanhui[i] =chuli(list[i]); //给返回数组赋值
         }
         return success("成功了",fanhui);
    }

    @GetMapping("/roadInfo/Test3")
    public AjaxResult test1(){
        return null;
    }
    public String chuli(String number){
        RoadInfo domain = new RoadInfo(); //新建domain
        domain.setNumber(Integer.valueOf(number));//设置number,转换为int类型
        try {
            String msg = "成功了";
            domain.setStatus("1");
            roadInfoService.addRoadInfo(domain);
            Thread.sleep(1000*5); //休眠3s，相当于计算用时花了3s
            return msg;
        }catch (Exception e){
            String msg = "失败";
            domain.setStatus("0");
            roadInfoService.addRoadInfo(domain);
            return msg;
        }
    }


}
