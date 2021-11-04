package com.example.demo.alice.announcement.service;

import com.example.demo.alice.announcement.domain.Announcement;

import java.util.List;
import java.util.Map;

public interface AnnouncementService {
    //获取最新的9条记录
    List<Announcement> getNine();
    //查询所有的公共记录
    List<Announcement> getAll();
    //根据id查询一个实体类
    Announcement getAnnouncementById(Integer id);
    //根据String查询包含这个String的集合
    List<Announcement> getAnnouncemetnByString(String keyword);

    /**
     * 得到所有表中的关键字数据,并统一返回给前端
     * @param keyword
     * @return
     */
    List<Map> getAllKeywords(String keyword);
}
