package com.example.demo.admin.roadInfoTest.domain;

/**
 * @author CTL
 * <p>实体类 </p>
 * 创建日期：2020-12-30 22:52
 */
public class RoadInfo {
    //主键id
    private int id;
    //随机数number
    private int number;
    //假定 是 标识是否插入成功的状态
    private String status;

    public RoadInfo(){}


    public RoadInfo(int id, int number, String status) {
        this.id = id;
        this.number = number;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RoadInfo{" +
                "id=" + id +
                ", number=" + number +
                ", status='" + status + '\'' +
                '}';
    }
}
