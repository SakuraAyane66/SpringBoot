package com.example.demo.alice.imgs.mapper;

import com.example.demo.alice.imgs.domain.Image;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageMapper {
    List<Image> getUrl(int number); //获取数据库中的最新number个url链接
}
