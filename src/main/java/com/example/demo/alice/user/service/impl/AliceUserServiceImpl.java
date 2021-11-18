package com.example.demo.alice.user.service.impl;

import com.example.demo.alice.user.domain.AliceUser;
import com.example.demo.alice.user.domain.OnlineUser;
import com.example.demo.alice.user.mapper.AliceUserMapper;
import com.example.demo.alice.user.mapper.OnlineUserMapper;
import com.example.demo.alice.user.service.AliceUserService;
import com.example.demo.common.utils.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.alibaba.druid.util.Utils.md5;

/**
 * @author CTL
 * <p>service层 </p>
 * 创建日期：2021-01-21 00:42
 */
@Service
public class AliceUserServiceImpl implements AliceUserService {
    @Resource
    private AliceUserMapper mapper;
    @Resource
    private OnlineUserMapper onlineUserMapper;

    //创建一个新用户
    @Override
    public String createUser(AliceUser user) {
        //不用判断是否存在，根据是否插入成功判断,目前会报错（mybatis报错，username唯一，暂时没有全局异常捕获，先不修改了）
        int isExist = mapper.isExist(user);
        //不存在，记录条数为0，创建新用户
        if(isExist==0){
            //可以在这随机生产用户的头像信息
            String salt = SaltUtil.creatSalt(5);//随机生产盐,盐默认长度为5
            user.setSalt(salt); //赋值
            user.setPassword(md5(user.getClearPassword()+salt)); //对明文密码加盐加密
//            user.setCreat_time(DateTransUtil.getDateTime(System.currentTimeMillis())); //在mybatis中自动创建时间
            mapper.createUser(user);
            return "创建成功！";
        }else{
            return "用户名已存在，请换个名字！";
        }
    }

    //通过手机注册的用户，自动设置用户名和密码，（该接口还未使用），分开写主要是对修改关闭，对扩展开放
    @Override
    public String creatUserByPhoneNumber(AliceUser user) {
        int isExist = mapper.isPhoneNumberExist(user.getPhonenumber());
        if(isExist==0){
            //该电话号未被注册
            //还需要加上随机头像url连接地址的函数
            user.setUsername(user.getPhonenumber()); //直接将电话赋值给用户名
            String clearPassword = SaltUtil.creatSalt(11); //电话生产的初始密码为11位
            String salt = SaltUtil.creatSalt(5); //生产的盐长度还是为5
            user.setClearPassword(clearPassword);  //设置明文密码
            user.setPassword(md5(clearPassword+salt)); //设置密码 ，因为数据库密码和用户不能为空
            mapper.createUser(user);
            return "创建成功！请登录！";
        }else {
         return "此电话号已经被注册，请直接登录！";
        }
    }

    //此处返回的内容要携带jwt，且手机和账号登录都调用该接口，所有登录都是这个接口
    //由于token是String，不能根据返回类型来判断是否登录成功，所以改为返回map了
    @Override
    public Map<String,String> login(HttpServletRequest request,AliceUser user) {
        //此处应该逻辑分离，应该直接返回token，剩余的插入日志，插入在线表等应该分离（消息队列）
        Map<String, String> resultMap = new HashMap<>();
        OnlineUser onlineUser = OnlineUtil.analysisHttp(request);
        onlineUser.setUsername(user.getUsername());//赋值username
        resultMap.put("token",null);
        resultMap.put("msg",null);
        //判断是否存在这个用户
        AliceUser trueUser = mapper.selectUser(user); //根据user的username或者id查询该user所有的信息，此处调用的是username
        //如果不存在该用户
        if(trueUser==null){
            onlineUser.setMsg("该用户名不存在!请检查用户名！");
            onlineUser.setStatus("0");
            //用户名不存在，手机账号登录也能通过该接口判断
            onlineUserMapper.insertLoginInfor(onlineUser);
            resultMap.put("msg","该用户名不存在!请检查用户名！");
            return resultMap;
        }
        //判断账号是否被禁
        String status = trueUser.getStatus(); //获取status状态，1是停用
        //如果账号达到解禁的时间点，登录的时候手动加逻辑判断刷新封禁状态
        if(status.equals("1")){
            //账号封禁，得到该账号的ban_time
            Date ban_time = trueUser.getBan_time();//获取用户的解禁时间
            //获取系统当前时间，如果已经过了封禁时间，将status置为0
            String now_time =DateTransUtil.getDateTime(System.currentTimeMillis());//获取当前时间，类型是String
            //当前时间在封禁时间之后
            if(DateTransUtil.compareTime(now_time,DateTransUtil.getDateTime(ban_time))){
                mapper.changeStatus(user); //更新封禁状态为0 （解封）
                //直接执行到下一步
            }else {
                int ban_year = DateTransUtil.getYear(ban_time); //获取解封的年份
                int now_year = DateTransUtil.getYear(); //获取当前年份
                //判断一定时间之后就返回为永久封禁
                if (ban_year != -1 && (ban_year - now_year) > 50) {
                    onlineUser.setMsg("该账户已被永久封禁！");
                    onlineUser.setStatus("0");
                    onlineUserMapper.insertLoginInfor(onlineUser);
                    resultMap.put("msg", "该账户已被永久封禁！");
                    return resultMap;
                } else {
                    onlineUser.setMsg("该账号将在" + DateTransUtil.getDateTime(ban_time) + "时间后解禁！");
                    onlineUser.setStatus("0");
                    onlineUserMapper.insertLoginInfor(onlineUser);
                    //返回该账号的解禁时间
                    resultMap.put("msg", "该账号将在" + DateTransUtil.getDateTime(ban_time) + "时间后解禁！");
                    return resultMap;
                }
            }
        }
//        手机账号登录
//        if(user.getPhonenumber()!=null && user.getPhonenumber()!=""){
//                //判断验证码是否正确，调用验证码接口
//                //返回登录成功的信息和需要的jwt
//                return "";
//        }else{//失败返回}
        //取盐
        String salt = trueUser.getSalt();//通过用户信息获取加密盐
        //加密初始密码,得到传入用户密码加密后的情况
        String password = md5(user.getClearPassword()+salt);
        //比较两者加密后的密码是否相同
        if(password.equals(trueUser.getPassword())){
            //此时刷新登录状态，并将用户添加进正在登录的表中
            onlineUserMapper.insertOnlineUser(onlineUser);
            //更新userinfo表中的最后登录ip，最后登录时间
            mapper.updateLastIpAndTime(onlineUser.getIpaddr());
            //此处应该和返回token的逻辑分开，需要加入到redis队列中去执行，毕竟要考虑高并发的时候（后面考虑用rb消息队列）
            //

            //生成token
            resultMap.put("token", JwtUtil.generate(user.getUsername()));
            //此处还要考虑是否要将token加入到redis中
            //插入登录日志
            onlineUser.setMsg("登录成功");
            onlineUser.setStatus("1");
            onlineUserMapper.insertLoginInfor(onlineUser);
            resultMap.put("msg","登录成功");
            return resultMap;
        }else {
            //插入登录日志
            onlineUser.setMsg("用户密码错误");
            onlineUser.setStatus("0");
            onlineUserMapper.insertLoginInfor(onlineUser);
            resultMap.put("msg","用户密码错误！");
            return resultMap;
        }
    }

    //手机登录接口方式应该单独分开，业务需要独立，程序要面向对象。解耦
}
