package com.example.demo.alice.publicconnect.controller;

import com.example.demo.alice.publicconnect.domain.Publicconnect;
import com.example.demo.alice.publicconnect.service.PublicconnectService;
import com.example.demo.common.base.AjaxResult;
import com.example.demo.common.base.BaseController;
import com.example.demo.common.base.BaseSearchMiddle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CTL
 * <p>公益链接控制器 </p>
 * 创建日期：2021-05-21 17:00
 */
@RestController
public class PublicconnectController extends BaseController {
    private final String ctx = "alice";
    @Autowired
    private PublicconnectService service;

    @GetMapping(ctx+"/getNinePublicconnect")
    public AjaxResult getNine(){
        List<Publicconnect> list = service.getNine();
        return success("成功了!",list);
    }

    //获取所有记录
    @GetMapping(ctx+"/getAll")
    public AjaxResult getAll(){
        List<Publicconnect> list = service.getAll();
        return success("成功了！",list);
    }

    //根据Id获取
    @PostMapping(ctx+"/getPubById")
    public AjaxResult getPubById(@RequestBody BaseSearchMiddle baseSearchMiddle){
        if(baseSearchMiddle.getId()==null){
            return error("请输入正确的参数");
        }
        Integer id = baseSearchMiddle.getId();
        Publicconnect pub = service.getPublicconnect(id);
        return success("成功了！",pub);
    }
    //根据关键词查询
    @PostMapping(ctx+"/getPubByString")
    public AjaxResult getPubByString(@RequestBody BaseSearchMiddle baseSearchMiddle){
        if(baseSearchMiddle.getKeyword()==null||baseSearchMiddle.getKeyword()==""){
            return error("keyword不能为空！");
        }
        String keyword = baseSearchMiddle.getKeyword();
        List<Publicconnect> list = service.getPublicconnectByString(keyword);
        return success("成功了！",list);
    }
}
