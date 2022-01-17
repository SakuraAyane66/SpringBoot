package com.example.demo.alice.imgs.service.impl;

import com.example.demo.alice.imgs.domain.Image;
import com.example.demo.alice.imgs.mapper.ImageMapper;
import com.example.demo.alice.imgs.service.ImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Resource
    private ImageMapper imageMapper; //引入mapper
    @Override
    public List<Image> getUrl(int n) {
        //具体逻辑在service中进行
//        String path = this.getClass().getResource("/static/img/1.JPG").getPath(); //不是想要的效果
//        System.err.println("path：" + path);
        String urlCtx = "http://localhost/imgs/";//这个必须要指向最新的url图片实际存储地址路径（可能不在同一个服务器上）
        List<Image> list = imageMapper.getUrl(n);
        for(Image ll:list){
            ll.setUrl(urlCtx+ll.getUrl());
            System.out.println("ll ? "+ll.getUrl());
        }
        return list;
    }
}
