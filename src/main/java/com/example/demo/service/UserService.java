package com.example.demo.service;

import com.example.demo.common.utils.RedisUtil;
import com.example.demo.common.utils.ThreadUtil;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserModel;
import com.example.demo.common.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisUtil redisUtil; //引入redis工具类，时间过期单位是秒（s）
    @Autowired //适用redis提供的接口
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;  //使用序列化之后的redis，但是K V值只能是String类型



    //返回根据id查询结果的对象
    public UserModel get(int id){//尝试在这里从上下文中username的获取
//        String currentUsername = UserContext.getCurrentUserName();
//        System.out.println("username"+currentUsername);
        return userMapper.get(id);
    }
    //获取所有数据
    public List<UserModel> getAll(){
        return userMapper.getAll();
    }

    //
    public UserModel getTestAll(){
        return userMapper.getTestAll();
    }
    //尝试这么写，看返回是不是String
    public String getName(int id){
        ValueOperations ops = redisTemplate.opsForValue(); //初始化使用,默认序列化方式（带16进制）

        redisTemplate.setKeySerializer(new StringRedisSerializer());//设置redis前面key的序列化格式
//        redisTemplate.setValueSerializer(new StringRedisSerializer());//设置redis前面value的序列化格式

        HashMap<String,String> ha = new HashMap<>();
        ha.put("sakura","ayane");
        ha.put("CTL","skydragon");
        ops.set("119",ha);
//        String x=ops.get("119");
        System.out.println(ops.get("119"));
//        ValueOperations t1 = stringRedisTemplate.opsForValue(); //初始化StringRedis使用
//        t1.set("118",ha,10,TimeUnit.MINUTES);
//        Object result1 = ops.get("119");
//        System.out.println("从redis中找到的hashmap是什么"+result1);
//
//        ops.set("119",ha,10,TimeUnit.MINUTES);
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
            ops.set(id+"_name",name,10, TimeUnit.MINUTES); //写入缓存中,时间，时间单位
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

    public boolean setRedisTest(){
        UserModel user = new UserModel();//新建user实体类
        user.setAge(27);
        user.setName("sakura");
        user.setAddress("Tokyo");
        user.setUsername("Ayane");
        user.setId(17);
        redisUtil.set("sakura","ayane");
        return redisUtil.set("ctl",user,60*60);
    }
    public UserModel getRedisTest(String name){
        UserModel user = (UserModel)redisUtil.get(name);
        return user;
    }

    //线程相关测试，并发查询，用户处理数据库查询时间太长
    public void getThreadTest() throws InterruptedException{
        long startTime=System.currentTimeMillis();   //获取开始时间
        for(int i=1;i<11;i++){
            //创建线程
            ThreadUtil t1=new ThreadUtil();
            t1.setId(i);
            t1.setName("我是测试线程！！！"+i);
            //启动线程
            t1.start();
            //************有问题！！///
            t1.join();  //这一句有大问题，要先全部线程start之后才能join，join是需要放在循环外的，
        }
//        ThreadUtil t1 = new ThreadUtil();
//        t1.setId(1);
//        t1.setName("我是测试线程！！！");
//        t1.start();
        System.out.println("线程调用start结束了");
        //开启start之后，查看运行的结果
        ThreadUtil.getList();
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("运行时间是"+(endTime-startTime)+"ms");
        System.out.println("线程类打印输出结束了");
    }
    //线程测试相关，确定循环执行mapper是不是顺序执行
    public void getTestMapperOrder(){
        long startTime=System.currentTimeMillis();   //获取开始时间
        //循环1~10
        for(int i=0;i<11;i++){
            UserModel userModel = userMapper.get(i); //循环获取到userModl
            System.out.println("获取了上一个的usermodel之后才会执行下一个的"+userModel);
        }
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("运行时间是"+(endTime-startTime)+"ms");
    }
}
