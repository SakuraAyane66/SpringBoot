package com.example.demo.common.utils;

import com.example.demo.others.Test.PrintWordsJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author CTL
 * <p>通过name和i，返回一个名字带有i的变量名 </p>
 * 失败了，这个工具不可用，也不能作为变量声明
 * 创建日期：2020-11-02 11:30
 */
//同种问题解决方法，用arraylist或者二维数组，反正不用变量名，但是可以通过其他方式来解决
public class NameUtil {
    public static String setName(String name,int i){
        String s = name+i+"";
        return s;
    }

    //各种测试的main函数
    public static void main(String[] args) throws SchedulerException, InterruptedException{
// 1、创建调度器Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 2、创建JobDetail实例，并与PrintWordsJob类绑定(Job执行内容)
        JobDetail jobDetail = JobBuilder.newJob(PrintWordsJob.class)
                .withIdentity("job1", "group1").build();
        // 3、构建Trigger实例,每隔1s执行一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
                .startNow()//立即生效
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(1)//每隔1s执行一次
                        .repeatForever()).build();//一直执行

        //4、执行
        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("--------scheduler start ! ------------");
        scheduler.start();

        //睡眠
        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
        System.out.println("--------scheduler shutdown ! ------------");

    }
}
