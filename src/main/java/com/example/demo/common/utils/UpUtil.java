package com.example.demo.common.utils;

import com.example.demo.admin.update.domain.RoadInformation;
import com.example.demo.admin.update.domain.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CTL
 * <p>取新list和数据库list的交集和差集 </p>
 * 创建日期：2020-11-05 00:21
 */
public class UpUtil {
    //看似高大尚，不如根据原来的id全部直接删了然后全部重新插入...
    //这样最后就提升一点效率罢了，假设每一条记录的实体类为Entity，唯一的编码属性为code。
    //取交集
    public static List<User> sameList(List<User> oldList,List<User> newList){
        List<User> resultList = oldList.stream()
                .filter(item -> newList.stream().map(e -> e.getId())  //这里的getId为编码的唯一属性
                        .collect(Collectors.toList()).contains(item.getId()))
                .collect(Collectors.toList());
        return resultList;
    }

    //取差集
    public static List<User> diffList(List<User> firstList,List<User> secondList){
        List<User> resultList = firstList.stream()
                .filter(item -> !secondList.stream().map(e -> e.getId())
                        .collect(Collectors.toList()).contains(item.getId()))
                .collect(Collectors.toList());
        return resultList;
    }

    //RoadInformation的交集和差集
    //取交集
    public static List<RoadInformation> sameRoadList(List<RoadInformation> oldList, List<RoadInformation> newList){
        List<RoadInformation> resultList = oldList.stream()
                .filter(item -> newList.stream().map(e -> e.getOrder_number())  //这里的getId为编码的唯一属性
                        .collect(Collectors.toList()).contains(item.getId()))
                .collect(Collectors.toList());
        return resultList;
    }

    //取差集
    public static List<RoadInformation> diffRoadList(List<RoadInformation> firstList,List<RoadInformation> secondList){
        List<RoadInformation> resultList = firstList.stream()
                .filter(item -> !secondList.stream().map(e -> e.getOrder_number())
                        .collect(Collectors.toList()).contains(item.getId()))
                .collect(Collectors.toList());
        return resultList;
    }
}
