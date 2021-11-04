package com.example.demo.alice.announcement.service.Impl;

import com.example.demo.alice.announcement.domain.Announcement;
import com.example.demo.alice.announcement.mapper.AnnouncementMapper;
import com.example.demo.alice.announcement.service.AnnouncementService;
import com.example.demo.alice.goodthings.domain.Goodthings;
import com.example.demo.alice.goodthings.mapper.GoodthingsMapper;
import com.example.demo.alice.publicactivity.domain.Publicactivity;
import com.example.demo.alice.publicactivity.mapper.PublicactivityMapper;
import com.example.demo.alice.publicconnect.domain.Publicconnect;
import com.example.demo.alice.publicconnect.mapper.PublicconnectMapper;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CTL
 * <p>service层 </p>
 * 创建日期：2021-04-12 11:11
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {
   @Resource
   private AnnouncementMapper announcementMapper;
   @Resource
   private GoodthingsMapper goodthingsMapper;
   @Resource
   private PublicactivityMapper publicactivityMapper;
   @Resource
   private PublicconnectMapper publicconnectMapper;
    @Override
    public List<Announcement> getNine() {
        return announcementMapper.getNine();
    }

    @Override
    public List<Announcement> getAll() {
        return announcementMapper.getAll();
    }

    @Override
    public Announcement getAnnouncementById(Integer id) {
        return announcementMapper.getAnnouncementById(id);
    }

    @Override
    public List<Announcement> getAnnouncemetnByString(String keyword) {
        return announcementMapper.getAnnouncemetnByString(keyword);
    }

    /**
     * 查询所有表关键词
     * @param keyword
     * @return
     */
    @Override
    public List<Map> getAllKeywords(String keyword) {
        List<Map> list = new ArrayList<>(); //返回的list
        Map<String,List<Announcement>> map1 = new HashMap<>();
        Map<String,List<Goodthings>> map2 = new HashedMap();
        Map<String,List<Publicactivity>> map3 = new HashedMap();
        Map<String,List<Publicconnect>> map4 = new HashedMap();
        map1.put("Announcement",announcementMapper.getAnnouncemetnByString(keyword));
        map2.put("Goodthings",goodthingsMapper.getGoodthingsByString(keyword));
        map3.put("Publicactivity",publicactivityMapper.getPublicactivityByString(keyword));
        map4.put("Publicconnect",publicconnectMapper.getPublicconnectByString(keyword));
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        return list;
    }
}
