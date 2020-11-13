package com.example.demo.admin.author.service.impl;

import com.example.demo.admin.author.domain.Author;
import com.example.demo.admin.author.service.AuthorService;
import com.example.demo.admin.author.mapper.AuthorMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CTL
 * <p>Author具体的实现 </p>
 * 创建日期：2020-10-23 17:48
 */
//实现service的接口
@Service
public class AuthorServiceImpl implements AuthorService {
    @Resource
    private AuthorMapper mapper;
    //获取所有的author信息
    @Override
    public List<Author> getAll(){
        return mapper.getAll();
    }

    //通过id获取author信息，传入的但是是author实体类
    @Override
    public Author getAuthorById(Author author) {
        Author author1 = mapper.getAuthorById(author);
        return author1;
    }
    //通过author获取地址
    @Override
    public String getAddById(Author author) {
        String add = mapper.getAddById(author);
        return add;
    }
    //关键！！通过author获取到user的信息
    @Override
    public Author getUserByAuthor(Author author) {
        //直接返回userModel
        Author userModel = mapper.getUserByAuthor(author);
        return userModel;
    }
    //通过i,j获取到相应范围内的address
    @Override
    public List<String> getAddsByIds(int i, int j) {
        List<String> adds = mapper.getAddsByIds(i,j);
        return adds;
    }
}
