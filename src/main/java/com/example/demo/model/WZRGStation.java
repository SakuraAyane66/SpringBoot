package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CTL
 * <p>测试接受类，等要删掉 </p>
 * 创建日期：2021-05-28 10:00
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WZRGStation {
        /**
         * 日期
         */
        private String riqi;

        /**
         * 时刻
         */
        private String shike;

        /**
         * 盘号
         */
        private String panhao;

        /**
         * 配比号
         */
        private String peibihao;

        /**
         * 车号
         */
        private String chehao;

        /**
         * 客户号
         */
        private String kehuhao;

        /**
         * 骨料10
         */
        private String guliao10;

        /**
         * 骨料9
         */
        private String guliao9;

        /**
         * 骨料8
         */
        private String guliao8;

        /**
         * 骨料7
         */
        private String guliao7;

        /**
         * 骨料6
         */
        private String guliao6;

        /**
         * 骨料5
         */
        private String guliao5;

        /**
         * 骨料4
         */
        private String guliao4;

        /**
         * 骨料3
         */
        private String guliao3;

        /**
         * 骨料2
         */
        private String guliao2;

        /**
         * 骨料1
         */
        private String guliao1;

        /**
         * 石粉5
         */
        private String shifen5;

        /**
         * 石粉4
         */
        private String shifen4;

        /**
         * 石粉3
         */
        private String shifen3;

        /**
         * 石粉2
         */
        private String shifen2;

        /**
         * 石粉1
         */
        private String shifen1;

        /**
         * 沥青
         */
        private String liqing;

        /**
         * 再生料
         */
        private String zaishengliao;

        /**
         * 添加剂
         */
        private String tianjiaji;

        /**
         * 合计kg
         */
        private String hejikg;

        /**
         * 3仓温度
         */
        private String wendu3cang;

        /**
         * 2仓温度
         */
        private String wendu2cang;

        /**
         * 1仓温度
         */
        private String wendu1cang;

        /**
         * 混合料温度
         */
        private String hunheliaowendu;

        /**
         * 除尘器入口
         */
        private String chuchenqirukou;

        /**
         * 沥青温度
         */
        private String liqingwendu;

        /**
         * 骨料温度
         */
        private String guliaowendu;
    }


