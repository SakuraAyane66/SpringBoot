package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserModel;
import com.example.demo.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ListResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * <h3>demo</h3>
 * <p>User 业务逻辑层</p>
 *
 * @author : CTL
 * @date : 2020-10-14 14:34
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired //适用redis提供的接口
    RedisTemplate redisTemplate;
    //返回根据id查询结果的对象
    public UserModel get(int id){//尝试在这里从上下文中username的获取
        String currentUsername = UserContext.getCurrentUserName();
        System.out.println("username"+currentUsername);
        return userMapper.get(id);
    }
    //获取所有数据
    public List<UserModel> getAll(){
        return userMapper.getAll();
    }
    //尝试这么写，看返回是不是String
    public String getName(int id){
        ValueOperations ops = redisTemplate.opsForValue(); //初始化使用
        String name;  //在getName中先声明要获取的信息
        Object id_name = ops.get(id+"_name");  //尝试从redis中取缓存,此时类型是Object
        System.out.println("id_name为"+id_name);//打印
        //从缓存中找到name
        if(id_name!=null){
            name = (String)id_name; //强制类型转换
            System.out.println("缓存中找到的name"+name);
        }else{
            name = userMapper.getName(id); //从数据库获取
            System.out.println("数据库中找到的name"+name);
            ops.set(id+"_name",name,10, TimeUnit.SECONDS); //写入缓存中,时间，时间单位
        }
        return name;
    }
    //增加,暂时看看void ,别人项目用的是bool
    public void addUser(UserModel user){
        userMapper.addUser(user);
    };
    //删除
    public void deleteUser(UserModel user){
        userMapper.deleteUser(user);
    }
    //更新,添加了注解！！这是事务的注解
    @Transactional(rollbackFor = Exception.class)//通过添加注解或去除注解来看是否生效,还需要在启动类中添加事务注解
    public void updateUser(UserModel user){
        userMapper.updateUser(user);
//        int a = 1/0;  //异常
    }
    //根据username查询email
    public List<String> getEmail(String username){
        List<String> list = userMapper.getEmail(username);
        return list;
    }
    //根据age查询记录
    public List<UserModel> getUserByAge(int age){
        List<UserModel> users = userMapper.getUserByAge(age);
        return users;
    }
    //根据username 和password找用户
    public UserModel findUser(String username,String password){
        UserModel user = userMapper.findUser(username,password);
        return user;
    }

    //测试连表查询
    public List<UserModel> getUsersAndAuthor(){
        List<UserModel> userModel = userMapper.getUsersAndAuthor();
        return userModel;
    }
}
