package com.example.demo.alice.announcement.controller;

import com.example.demo.alice.announcement.domain.Announcement;
import com.example.demo.alice.announcement.service.AnnouncementService;
import com.example.demo.common.base.AjaxResult;
import com.example.demo.common.base.BaseController;
import com.example.demo.common.base.BaseSearchMiddle;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author CTL
 * <p>公告暴露出的接口控制器 </p>
 * 创建日期：2021-04-12 11:06
 */
@RestController
public class AnnouncementController extends BaseController {
    private final String ctx = "alice"; //以后更改ctx的内容，下面所有的内容都可以一起修改了，方便管理
    @Autowired
    private AnnouncementService announcementService;

    //获取最新9条记录
    @GetMapping(ctx+"/getNineAnnouncement")
    @ResponseBody
    public AjaxResult getNine(){
        List<Announcement> list = announcementService.getNine();
        return success("成功获取",list);
    }

    //获取所有记录
    @GetMapping(ctx+"/getAllAnnouncement")
    @ResponseBody
    public AjaxResult getAll(){
        List<Announcement> list = announcementService.getAll();
        return success("成功获取",list);
    }

    //根据id获取具体的记录
    @PostMapping(ctx+"/getAnnouncementById")
    @ResponseBody
    public AjaxResult getById(@RequestBody BaseSearchMiddle baseSearchMiddle){
        if(baseSearchMiddle.getId()==null){
            return error("请输入正确的参数");
        }
        Integer id = baseSearchMiddle.getId();
        Announcement announcement = announcementService.getAnnouncementById(id);
        System.out.println(announcement);
        if(announcement==null){
            //为null，数据库找不到该数据库
            return error(206,"查无此id数据");
        }
        return success("成功获取",announcement);
    }

    //根据关键词查询记录
    @PostMapping(ctx+"/getAnnouncementByString")
    @ResponseBody
    public AjaxResult getByKeyword(@RequestBody BaseSearchMiddle baseSearchMiddle){
        if(baseSearchMiddle.getKeyword()==null||baseSearchMiddle.getKeyword()==""){
            return error("keyword不能为空！");
        }
        String keyword = baseSearchMiddle.getKeyword();
        List<Announcement> list = announcementService.getAnnouncemetnByString(keyword);
        return success("成功获取",list);
    }

    //根据关键词查询记录，所有表（4个主数据表）中的记录
    @PostMapping(ctx+"/getAllKeyword")
    @ResponseBody
    public AjaxResult getAllKeyword(@RequestBody BaseSearchMiddle baseSearchMiddle){
        System.out.println("keyword?"+baseSearchMiddle.getKeyword());
        if (baseSearchMiddle.getKeyword()==null || baseSearchMiddle.getKeyword() =="") {
            return error(200,"关键词为空","");
        }
        List<Map> list = announcementService.getAllKeywords(baseSearchMiddle.getKeyword());
        return success("成功返回",list);
    }
}
