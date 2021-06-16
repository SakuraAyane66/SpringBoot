package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CTL
 * <p>分表所需要的domain学生类 </p>
 * 创建日期：2021-06-11 15:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    //主键uuid
    private String id;
    //分片的主键age（暂时不用dbn作为分片的主键）
    private int age;
    //name属性
    private String name;
}
