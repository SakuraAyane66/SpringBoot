package com.example.demo.update.domain;

/**
 * @author CTL
 * <p>导入道路信息excel数据的实体类 </p>
 * 创建日期：2020-11-05 16:27
 */
public class RoadInformation {
    private Integer id; //主键，唯一id
    private int order_number; //序号
    private double x;  //x坐标
    private double y;  //y坐标
    private String type; //类型
    private double lat; //经纬度
    private double lon; //经纬度
    private String method; //填方or挖方
    private double surface; //面层宽度
    private double up; //上基层宽度
    private double down; //下基层宽度
    private double bottom; //底基层宽度
    private int belong;  //属于什么编号（判断具体是那一次表提交的记录的标识）

    public RoadInformation(int order_number, double x, double y, String type, double lat,
                           double lon, String method, double surface, double up, double down,
                           double bottom, int belong) {
        this.order_number = order_number;
        this.x = x;
        this.y = y;
        this.type = type;
        this.lat = lat;
        this.lon = lon;
        this.method = method;
        this.surface = surface;
        this.up = up;
        this.down = down;
        this.bottom = bottom;
        this.belong = belong;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getOrder_number() {
        return order_number;
    }

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public double getUp() {
        return up;
    }

    public void setUp(double up) {
        this.up = up;
    }

    public double getDown() {
        return down;
    }

    public void setDown(double down) {
        this.down = down;
    }

    public double getBottom() {
        return bottom;
    }

    public void setBottom(double bottom) {
        this.bottom = bottom;
    }

    public int getBelong() {
        return belong;
    }

    public void setBelong(int belong) {
        this.belong = belong;
    }
    //还是方便
    @Override
    public String toString() {
        return "RoadInformation{" +
                "id=" + id +
                ", order_number=" + order_number +
                ", x=" + x +
                ", y=" + y +
                ", type='" + type + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", method='" + method + '\'' +
                ", surface=" + surface +
                ", up=" + up +
                ", down=" + down +
                ", bottom=" + bottom +
                ", belong=" + belong +
                '}';
    }
}
