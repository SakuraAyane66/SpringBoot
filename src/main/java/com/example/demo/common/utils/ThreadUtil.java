package com.example.demo.common.utils;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CTL
 * <p>线程测试类 </p>
 * 创建日期：2021-04-25 15:23
 */
//@Component  //添加注解，普通组件
public class ThreadUtil extends Thread {
    //public static ThreadUtil threadUtil; //要在普通类中使用mapper或者service服务，需要这样写
    @Resource
    private UserMapper mapper; //注入mapper层
    public static List<UserModel> list = new ArrayList<>(); //静态变量list，存放单个线程的结果
    private int id; //实体线程具有的id

    //在普通类使用mapper或者service
//    @PostConstruct //注入bean
//    public void init(){
//        threadUtil = this ;
//        //第四步：调用mapper或service接口
//        threadUtil.mapper = this.mapper;
//    }

    @Override
    public void run() {
        try {
            System.out.println("进入了run方法");
            System.out.println("不能获取到id？？？？" + this.id);
            mapper = BeanContext.getApplicationContext().getBean(UserMapper.class);

            //重写run方法，这里实现将结果user添加进list
            UserModel userModel = mapper.get(this.id);
            list.add(userModel);
            System.out.println("id为" + this.id + "的线程执行完毕");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("run异常"+e);
        }
    }

    //获取全局全部的list
    public static void getList(){
        for(UserModel ll:list){
            System.out.println("循环里面的ll是"+ll);
        }
    }

    //提供共有方法使其获得id
    public void setId(int i){
        this.id= i;
    }
}
