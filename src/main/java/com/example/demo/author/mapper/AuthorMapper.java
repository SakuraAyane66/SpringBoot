package com.example.demo.author.mapper;

import com.example.demo.author.domain.Author;
import com.example.demo.model.UserModel;

import java.util.List;

public interface AuthorMapper {
    //获取所有的author信息
    public List<Author> getAll();
    //根据id获取对应的信息，传入还是传入实体对象
    public Author getAuthorById(Author author);
    //根据id查询对应的地址，传入依旧是对象
    public String getAddById(Author author);
    //根据范围查询范围内ID所属对象的所有地址，并且按照id顺序排序地址顺序
    public List<String> getAddsByIds(int i,int j);

    //重点：根据对象查询外键表user_id对应的user对象内容
    public UserModel getUserByAuthor(Author author);

}
