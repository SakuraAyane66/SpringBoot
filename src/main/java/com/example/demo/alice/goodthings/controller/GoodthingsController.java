package com.example.demo.alice.goodthings.controller;

import com.example.demo.alice.announcement.domain.Announcement;
import com.example.demo.alice.goodthings.domain.Goodthings;
import com.example.demo.alice.goodthings.service.Impl.GoodthinsService;
import com.example.demo.common.base.AjaxResult;
import com.example.demo.common.base.BaseController;
import com.example.demo.common.base.BaseSearchMiddle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.common.base.AjaxResult.success;

/**
 * @author CTL
 * <p>好人好事相关的控制器 </p>
 * 创建日期：2021-04-15 17:01
 */
@RestController
public class GoodthingsController extends BaseController {
    private final String ctx = "alice";
    @Autowired
    private GoodthinsService goodthinsService;

    @GetMapping(ctx+"/getNineGoodthings")
    public AjaxResult getNine(){
        List<Goodthings> list = goodthinsService.getNine();
        return success("成功了！",list);
    }

    //获取所有记录
    @GetMapping(ctx+"/getAllGoodthings")
    public AjaxResult getAll(){
        List<Goodthings> list =goodthinsService.getAll();
        return success("成功获取",list);
    }

    //根据id获取具体的记录
    @PostMapping(ctx+"/getGoodthingsById")
    public AjaxResult getById(@RequestBody BaseSearchMiddle baseSearchMiddle){
        if(baseSearchMiddle.getId()==null){
            return error("请输入正确的参数");
        }
        Integer id = baseSearchMiddle.getId();
        Goodthings goodthings = goodthinsService.getGoodthingsByid(id);
        return success("成功获取",goodthings);
    }

    //根据关键词查询记录
    @PostMapping(ctx+"/getGoodthingsByString")
    public AjaxResult getByKeyword(@RequestBody BaseSearchMiddle baseSearchMiddle){
        if(baseSearchMiddle.getKeyword()==null||baseSearchMiddle.getKeyword()==""){
            return error("keyword不能为空！");
        }
        String keyword = baseSearchMiddle.getKeyword();
        List<Goodthings> list = goodthinsService.getGoodthingsByString(keyword);
        return success("成功获取",list);
    }

}
