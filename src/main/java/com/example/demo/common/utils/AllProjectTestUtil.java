package com.example.demo.common.utils;

import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
//        for(UserModel ll:list){
//            System.out.println("每一个usermodel是？==="+ll);
//        }
    }

    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        System.out.println(list!=null);
        String start = "K1756+258";
        String truereg="\\+[0-9]{3}";
        Pattern pattern1 = Pattern.compile(truereg);//编译正则
        Matcher matcher = pattern1.matcher(start);
        List<String> matchStrs = new ArrayList<>();
         while (matcher.find()) { //此处find（）每次被调用后，会偏移到下一个匹配
            matchStrs.add(matcher.group());//获取当前匹配的值
        }

        for (int i = 0; i < matchStrs.size(); i++) {
            System.out.println("pattern1是    " +
                    ""+matchStrs.get(i));
        }
        matchStrs.get(0).replaceAll("\\+","");
        String reg2 = ".*(?<=\\+)(.*)";
        int kStart2 = Integer.parseInt(start.replaceAll(reg2,"$1"));
        System.out.println(kStart2);
        String reg = "^[a-zA-Z0-9]+\\+[0-9]+";

        //字符串a++++bcd中有4个星号,转义加号+
        String str3 = "a++++bcd";
        System.out.println(str3);
        String result3 = str3.replaceAll("\\+", "");
        System.out.println(result3);

//        String reg = "\\+[0-9]+";
//        String str = "a=123456aa6789";
//        String reg = ".*(?<=a=)(.*)(?=aa).*";
//        System.out.println (str.replaceAll (reg, "$1"));

//        String reg2 = ".*(?<=\\+)(.*)(?=\\.).*";

        //循环每一条数据


//        String end = "K1757+300.0";
//        System.out.println("是什么"+end.replaceAll(reg2,"$1"));
        String reg1 = ".*(?<=K)(.*)(?=\\+).*";
//        int NumberEnd = Integer.parseInt(end.replaceAll(reg1,"$1"));
        int NumberStart = Integer.parseInt(start.replaceAll(reg1,"$1"));

//        System.out.println("int?"+NumberEnd+"  "+NumberStart);

        Pattern patten = Pattern.compile(reg);//编译正则表达式

//        Matcher matcher = patten.matcher(end);// 指定要匹配的字符串
//
//        List<String> matchStrs = new ArrayList<>();
//
//        while (matcher.find()) { //此处find（）每次被调用后，会偏移到下一个匹配
//            matchStrs.add(matcher.group());//获取当前匹配的值
//        }
//        for (int i = 0; i < matchStrs.size(); i++) {
//            System.out.println(matchStrs.get(i));
//        }

        String m = "(2abcd)";
        boolean n=m.contains("(");
        System.out.println("n?="+n);
    }
}
