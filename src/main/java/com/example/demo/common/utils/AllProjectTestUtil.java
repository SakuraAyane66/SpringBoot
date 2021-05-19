package com.example.demo.common.utils;

import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CTL
 * <p>测试@PostConstuct是否只会执行一次 </p>
 * 创建日期：2021-04-28 15:37
 */
@Component
public class AllProjectTestUtil {
    //存放userModel
    public static final List<UserModel>  list= new ArrayList<>();
    @Resource
    private UserService service;

    //测试结果只会执行一次，在程序启动初始化的时候执行。
    @PostConstruct
    public void init(){
        //获取所有的User
        List<UserModel> allUser = service.getAll();
        //将得到的user添加进list中
        for(UserModel ll:allUser){
            list.add(ll);
        }
        for(UserModel ll:list){
            System.out.println("每一个usermodel是？==="+ll);
        }
    }
}
