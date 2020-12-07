package com.example.demo.admin.author.controller;

import com.example.demo.admin.author.domain.Author;
import com.example.demo.admin.author.service.AuthorService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CTL
 * <p>Author控制层 </p>
 * 创建日期：2020-10-26 11:24
 */
@RestController
public class AuthorController {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);
    @Autowired
    private AuthorService authorService;
    //  @Resource(name = "Author")  //注入实体service的名字，可以根据这个切换数据源，所以有设计impl和接口的必要

    @RequestMapping("/getAddsByIds")
    public List<String> getAddsByIds(@RequestParam("i") int i,@RequestParam("j") int j) {
        try{
            System.out.println("i为"+i+"j为"+j);
            List<String> adds = authorService.getAddsByIds(i,j);
            System.out.println("adds是否能查询"+adds);
            return adds;
        }catch (Exception e){
            System.out.println("失败了，日志也输出debug");
            System.out.println(e);
            logger.info("debug");
            return null;
        }
    }
    @RequestMapping("/d")
    public List<Author> getAll(){
        //pageHelper放在c层中
        PageHelper.startPage(1,5);
        List<Author> authors = authorService.getAll();
        return authors;
    }

    @RequestMapping("/getAuthorById")
    public Author getAuthorById(@RequestParam("i") int i){
        Author author = new Author();
        author.setId(i);
        Author author1 = authorService.getAuthorById(author);
        return author1;
    }
    //返回userModel，根据Author
    @RequestMapping("/getUserByAuthor")
    public Author getUserByAuthor(@RequestParam("id") int id,
                                     @RequestParam("name") String name,
                                     @RequestParam("userid") int user_id,
                                     @RequestParam("addr") String addr){
        Author author = new Author(id,name,user_id,addr);
        Author userModel = authorService.getUserByAuthor(author);
        System.out.println("userModel是"+userModel);
        return userModel;
    }
}
