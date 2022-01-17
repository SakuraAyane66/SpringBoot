package com.example.demo.alice.imgs.service;

import com.example.demo.alice.imgs.domain.Image;

import java.util.List;

public interface ImageService {
    List<Image> getUrl(int n); //获取最新的前n个图片url地址
}
