package com.example.demo.alice.announcement.service.Impl;

import com.example.demo.alice.announcement.domain.Announcement;
import com.example.demo.alice.announcement.mapper.AnnouncementMapper;
import com.example.demo.alice.announcement.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CTL
 * <p>service层 </p>
 * 创建日期：2021-04-12 11:11
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {
   @Resource
   private AnnouncementMapper announcementMapper;

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
}
