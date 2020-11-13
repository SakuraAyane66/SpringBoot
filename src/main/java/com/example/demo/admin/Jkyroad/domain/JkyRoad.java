package com.example.demo.admin.Jkyroad.domain;

/**
 * @author CTL
 * <p>domain </p>
 * 创建日期：2020-11-13 15:54
 */
public class JkyRoad {
    /**
     * 道路每条数据的唯一id
     */
    private String roadId;

    /**
     * excel中数据序号,“开始"序号为0，"终点"序号为最后条序号+1
     */
    private int orderNumber;

    /**
     * 桩号信息
     */
    private String stakeNumber;

    /**
     * 数据的类型
     */
    private String type;

    /**
     * x坐标
     */
    private double xCoordinate;

    /**
     * y坐标
     */
    private double yCoordinate;

    /**
     * lat
     */
    private double lat;

    /**
     * lon
     */
    private double lon;

    /**
     * 方法， 挖方or填方
     */
    private String method;

    /**
     *  surface面层
     */
    private double surface;

    /**
     * up 上基层
     */
    private double up;

    /**
     * down 下基层
     */
    private double down;

    /**
     * bottom 底基层
     */
    private double bottom;

    /**
     * belong 标识具体那一次提交的全部数据，可有文件名转义作为唯一标识，或者用户前端选择
     */
    private String belong;  //目前通过文件名试一试

    private Long delpId; //所属项目id

    public Long getDelpId() {
        return delpId;
    }

    public void setDelpId(Long delpId) {
        this.delpId = delpId;
    }

    public JkyRoad(){} //无参构造器
    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getStakeNumber() {
        return stakeNumber;
    }

    public void setStakeNumber(String stakeNumber) {
        this.stakeNumber = stakeNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
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

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public JkyRoad(String roadId, int orderNumber, String stakeNumber,
                   String type, double xCoordinate, double yCoordinate,
                   double lat, double lon, String method, double surface,
                   double up, double down, double bottom, String belong) {
        this.roadId = roadId;
        this.orderNumber = orderNumber;
        this.stakeNumber = stakeNumber;
        this.type = type;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.lat = lat;
        this.lon = lon;
        this.method = method;
        this.surface = surface;
        this.up = up;
        this.down = down;
        this.bottom = bottom;
        this.belong = belong;
    }

    @Override
    public String toString() {
        return "JkyRoad{" +
                "roadId='" + roadId + '\'' +
                ", orderNumber=" + orderNumber +
                ", stakeNumber='" + stakeNumber + '\'' +
                ", type='" + type + '\'' +
                ", xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                ", lat=" + lat +
                ", lon=" + lon +
                ", method='" + method + '\'' +
                ", surface=" + surface +
                ", up=" + up +
                ", down=" + down +
                ", bottom=" + bottom +
                ", belong='" + belong + '\'' +
                '}';
    }
}
