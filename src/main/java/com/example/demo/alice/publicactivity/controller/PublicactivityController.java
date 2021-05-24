package com.example.demo.alice.publicactivity.controller;

import com.example.demo.alice.goodthings.domain.Goodthings;
import com.example.demo.alice.publicactivity.domain.Publicactivity;
import com.example.demo.alice.publicactivity.service.PublicactivityService;
import com.example.demo.common.base.AjaxResult;
import com.example.demo.common.base.BaseController;
import com.example.demo.common.base.BaseSearchMiddle;
import org.hibernate.query.criteria.internal.path.ListAttributeJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author CTL
 * <p>公益活动控制器 </p>
 * 创建日期：2021-05-21 16:31
 */
@RestController
public class PublicactivityController extends BaseController {
    private final String ctx = "alice";
    @Autowired
    private PublicactivityService service;

    //查询最新的9条记录，用于首页展示
    @GetMapping(ctx+"/getNinePublicactivity")
    public AjaxResult getNine(){
        List<Publicactivity> list = service.getNine();
        return success("成功了",list);
    }
    //获取全部的记录
    @GetMapping(ctx+"/getAllPublicactivity")
    public AjaxResult getAll(){
        List<Publicactivity> list = service.getAll();
        return success("成功了",list);
    }
    //根据id获取具体的记录
    @PostMapping(ctx+"/getPublicactivityById")
    public AjaxResult getPublicactivityById(@RequestBody BaseSearchMiddle baseSearchMiddle){
        if(baseSearchMiddle.getId()==null){
            return error("请输入正确的参数");
        }
        Integer id = baseSearchMiddle.getId();
        Publicactivity publicactivity = service.getPublicactivity(id);
        return success("成功获取",publicactivity);
    }

    //根据keyword查询
    @PostMapping(ctx+"/getPublicactivityByString")
    public AjaxResult getPublicactivityByString(@RequestBody BaseSearchMiddle baseSearchMiddle){
        if(baseSearchMiddle.getKeyword()==null||baseSearchMiddle.getKeyword()==""){
            return error("keyword不能为空！");
        }
        String keyword = baseSearchMiddle.getKeyword();
        List<Publicactivity> list = service.getPublicactivityByString(keyword);
        return success("成功获取！",list);
    }
}
