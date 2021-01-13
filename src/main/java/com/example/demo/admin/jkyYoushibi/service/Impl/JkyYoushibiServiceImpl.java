package com.example.demo.admin.jkyYoushibi.service.Impl;

import com.example.demo.admin.jkyYoushibi.domain.JkyYoushibi;
import com.example.demo.admin.jkyYoushibi.mapper.JkyYoushibiMapper;
import com.example.demo.admin.jkyYoushibi.service.jkyYoushibiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author CTL
 * <p>service 业务逻辑层 </p>
 * 创建日期：2021-01-13 10:30
 */
@Service
public class JkyYoushibiServiceImpl implements jkyYoushibiService {
    @Resource
    private JkyYoushibiMapper mapper;

    @Override
    public List<JkyYoushibi> getAll() {
        List<JkyYoushibi> list = mapper.getAll();
        getYoushibi2(list);
        return list;
    }

    //骨料重量是累加的情况得到油石比,取骨料的最大值，石料最大值进行计算
    public String[] getYoushibi2(List<JkyYoushibi> jkyYoushibis){
        //计算油石比曲线
        String[] ratioNums = new String[jkyYoushibis.size()];
        int flag = 0; //数组顺序
        //循环list中的每一个对象，找出该组对应的油石比
        for(JkyYoushibi youshibi:jkyYoushibis){
            Field[] fields = youshibi.getClass().getDeclaredFields();//反射获取所有的字段
            Double weight = 0.0; //总重量，骨料加石粉
            Double shifen=0.0; //石粉总重量，石粉一般不是累加，单独计算
            Double liqing = 0.0; //沥青总重量
            //新建double数组list
            List<Double> nums=new ArrayList<Double>();

            //循环字段获取数据
            try{
                for(Field ff:fields){
                    ff.setAccessible(true);
                    if(ff.getName().contains("guliao") && ff.get(youshibi)!=null&&!ff.getName().contains("guliaowendu")){
                            nums.add((Double)ff.get(youshibi)); //骨料添加进nums中
                    }
                    if(ff.getName().contains("shifen")&&ff.get(youshibi)!=null){
                            shifen += (Double) ff.get(youshibi); //石粉需要单独分开计算，不能笼统取最大，石粉一般不是累加的
                    }
                    if(ff.getName().contains("liqing")&&ff.get(youshibi)!=null){
                            liqing+= (Double) ff.get(youshibi);
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            //找出最大值
            Double Max = Collections.max(nums);  //骨料最大
            weight =Max+shifen;
            System.out.println("在第"+flag+"次循环中的Max为"+Max);
            System.out.println("在第"+flag+"次循环中的骨料和石粉总重量为"+weight);
            DecimalFormat df = new DecimalFormat("0.00");//十进制格式化，四舍五入留小数点后2位
            ratioNums[flag] = df.format((liqing/weight)*100);//得到油石比已经是显示百分比的值了，乘了100
            System.out.println("在第"+flag+"次循环中的油石比为"+ratioNums[flag]);
            flag++;
         }
        return ratioNums;
    }
}
