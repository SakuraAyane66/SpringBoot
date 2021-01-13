package com.example.demo.admin.jkyYoushibi.domain;

/**
 * @author CTL
 * <p>测试油石比的计算公式2 </p>
 * 创建日期：2021-01-13 10:15
 */
public class JkyYoushibi {
    //主键id 数据库中的类型为bigInt
    private Long id ;

    //骨料们
    private Double guliao1;
    private Double guliao2;
    private Double guliao3;
    private Double guliao4;
    private Double guliao5;
    private Double guliao6;


    //石粉们
    private Double shifen1;
    private Double shifen2;
    private Double shifen3;
    private Double shifen4;

    private Double liqing;

    //伪字段
    private Double totalWeight;

    public JkyYoushibi(Long id, Double guliao1, Double guliao2,
                       Double guliao3, Double guliao4, Double guliao5,
                       Double guliao6, Double shifen1, Double shifen2, Double shifen3, Double shifen4, Double liqing) {
        this.id = id;
        this.guliao1 = guliao1;
        this.guliao2 = guliao2;
        this.guliao3 = guliao3;
        this.guliao4 = guliao4;
        this.guliao5 = guliao5;
        this.guliao6 = guliao6;
        this.shifen1 = shifen1;
        this.shifen2 = shifen2;
        this.shifen3 = shifen3;
        this.shifen4 = shifen4;
        this.liqing = liqing;
    }

    @Override
    public String toString() {
        return "JkyYoushibi{" +
                "id=" + id +
                ", guliao1=" + guliao1 +
                ", guliao2=" + guliao2 +
                ", guliao3=" + guliao3 +
                ", guliao4=" + guliao4 +
                ", guliao5=" + guliao5 +
                ", guliao6=" + guliao6 +
                ", shifen1=" + shifen1 +
                ", shifen2=" + shifen2 +
                ", shifen3=" + shifen3 +
                ", shifen4=" + shifen4 +
                ", liqing=" + liqing +
                '}';
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGuliao1() {
        return guliao1;
    }

    public void setGuliao1(Double guliao1) {
        this.guliao1 = guliao1;
    }

    public Double getGuliao2() {
        return guliao2;
    }

    public void setGuliao2(Double guliao2) {
        this.guliao2 = guliao2;
    }

    public Double getGuliao3() {
        return guliao3;
    }

    public void setGuliao3(Double guliao3) {
        this.guliao3 = guliao3;
    }

    public Double getGuliao4() {
        return guliao4;
    }

    public void setGuliao4(Double guliao4) {
        this.guliao4 = guliao4;
    }

    public Double getGuliao5() {
        return guliao5;
    }

    public void setGuliao5(Double guliao5) {
        this.guliao5 = guliao5;
    }

    public Double getGuliao6() {
        return guliao6;
    }

    public void setGuliao6(Double guliao6) {
        this.guliao6 = guliao6;
    }

    public Double getShifen1() {
        return shifen1;
    }

    public void setShifen1(Double shifen1) {
        this.shifen1 = shifen1;
    }

    public Double getShifen2() {
        return shifen2;
    }

    public void setShifen2(Double shifen2) {
        this.shifen2 = shifen2;
    }

    public Double getShifen3() {
        return shifen3;
    }

    public void setShifen3(Double shifen3) {
        this.shifen3 = shifen3;
    }

    public Double getShifen4() {
        return shifen4;
    }

    public void setShifen4(Double shifen4) {
        this.shifen4 = shifen4;
    }

    public Double getLiqing() {
        return liqing;
    }

    public void setLiqing(Double liqing) {
        this.liqing = liqing;
    }
}
