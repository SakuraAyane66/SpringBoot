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

    //批量更新 ，单纯的批量更新，在业务逻辑上梳理之后进行
    public int updateUsers(List<User> users);

    //批量更新，用在数据出现问题的时候，在逻辑中是判断主键是否存在
    public int updateUsersPlus(List<User> users);

    //判断主键在数据库中是否存在,传的是user
    public int isExist(User user);

    //根据用户上传的项目所属id查询所以属于该次上传的集合(可以根据上传文件名生成的唯一标识)
    public List<User> getUsers(int i);
    public List<User> getUsers(String fileName); //还没完成的接口

    //批量删除操作，根据取差集的结果进行批量删除
    public int deleteUsers(List<User> users);

}
