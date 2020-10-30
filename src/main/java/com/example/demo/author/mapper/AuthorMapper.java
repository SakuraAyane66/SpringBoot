package com.example.demo.author.mapper;

import com.example.demo.author.domain.Author;
import com.example.demo.model.UserModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//这里必须加上mapper，项目没加是在启动项配置了全局自动扫描mapper
@Mapper
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
    public Author getUserByAuthor(Author author);

    //模糊多条件查询，都是有才变成查询的限制条件，没有那么就直接查询
    //参数 a_i,a_j author的id范围，a_name 包含name字段！！
    // u_i,u_j user表的id范围，String addr 如果有则必须与user中的address相同
    public List<Author> findByMany(int a_i,int a_j,String a_name,int u_i,int u_j,String addr);
}