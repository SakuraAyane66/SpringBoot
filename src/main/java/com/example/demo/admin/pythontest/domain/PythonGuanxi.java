package com.example.demo.admin.pythontest.domain;

/**
 * @author CTL
 * <p>python实体类 </p>
 * 创建日期：2020-12-16 09:36
 */
public class PythonGuanxi {
    //数据库中的主键id
    private Integer id;
    //数据库中的对应关系值，在数据库中是TEXT格式，对应java的string
    private String value;
    //关键词，k-v前面的key
    private String keyname;



    public String getKeyname() {
        return keyname;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname;
    }
    public PythonGuanxi(Integer id, String value,String keyname) {
        this.id = id;
        this.value = value;
        this.keyname = keyname;
    }
    public PythonGuanxi(){}; //无参构造

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PythonGuanxi{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", keyname='" + keyname + '\'' +
                '}';
    }
}
