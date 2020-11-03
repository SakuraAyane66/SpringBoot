package com.example.demo.update.mapper;

import com.example.demo.model.UserModel;
import com.example.demo.update.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * eccel表中的user类mapper，循环单条插入和批量插入
 */
@Mapper
public interface ExcelUserMapper {
    //根据传入的Excel user 对象插入到数据库当中
    //返回值设为了int,插入单个对象
    public int addUser(User user);

    //批量插入，插入的是user的集合,返回执行条数
    public int addUsers(List<User> users);

}
