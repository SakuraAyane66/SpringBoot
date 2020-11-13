package com.example.demo.admin.author.controller;

import com.example.demo.admin.author.domain.Author;
import com.example.demo.admin.author.service.AuthorService;
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
    //  @Resource(name = "Author")  //注入实体service的名字，可以根据这个切换数据源，所以有设计impl和接口的必要
    private AuthorService authorService;

    @RequestMapping("/getAddsByIds")
    public List<String> getAddsByIds(@RequestParam("i") int i,@RequestParam("j") int j) {
        try{
            List<String> adds = authorService.getAddsByIds(i,j);
            return adds;
        }catch (Exception e){
            System.out.println("失败了，日志也输出debug");
            logger.info("debug");
            return null;
        }
    }
    @RequestMapping("/getAuthorAll")
    public List<Author> getAll(){
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
