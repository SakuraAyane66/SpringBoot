package com.example.demo.alice.announcement.mapper;

import com.example.demo.alice.announcement.domain.Announcement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnouncementMapper {
    //查询最新9条数据
    List<Announcement> getNine();
    //查询所有的公共记录
    List<Announcement> getAll();

    //简单版本暂时就算了吧，就按照普通的之前的功能写，之后更新迭代的时候加上吧
    //根据id查询一个实体类
    Announcement getAnnouncementById(Integer id);
    //根据String 关键词查询包含这个String的集合
    List<Announcement> getAnnouncemetnByString(String keywrod);

}
