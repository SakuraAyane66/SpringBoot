package com.example.demo.mapper;

import com.example.demo.model.UserModel;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//mapper/DAO层实现与数据库的接口，在mapper.xml文件中实现接口的方法，查询数据库相关信息,在service层被调用

@Mapper
public interface UserMapper {
    //根据id获取信息
    UserModel get(int id);
    //根据id找names
    String getName(int id);
    //获取所有的信息
    List<UserModel> getAll();
    //果然可以设置为void 或者 int根据自己需要选择，里面都是传入的实体类
    //增加 ,里面的参数是实体类对象，
    void addUser(UserModel user);
    //删除
    void deleteUser(UserModel user);
    //修改
    void updateUser(UserModel user);

    //根据username获取所有的email，
    List<String> getEmail(String username);
    //换一个函数,获取age为xx的集合
    List<UserModel> getUserByAge(int age);

    //根据username和password查询user
    UserModel findUser(String username,String password);

    //测试，根据user的id关联到author的user_id查询，主要是返回user主体 //
    List<UserModel> getUsersAndAuthor();  //int id //先注释看效果

}
