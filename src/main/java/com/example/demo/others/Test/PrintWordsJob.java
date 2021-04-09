package com.example.demo.others.Test;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author CTL
 * <p>quzrtz的定时器框架中的job </p>
 * 创建日期：2021-04-07 17:24
 */
@DisallowConcurrentExecution
//不允许框架在同一时刻调用同一个job类（比如job1的运行时间是10s，但间隔只有5s，开启该注解会让上一次10s的job1执行完在执行下一个job1）
public class PrintWordsJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //重写execute方法
        String printTime = new SimpleDateFormat("yy-MM-dd HH-mm-ss").format(new Date());
        System.out.println("PrintWordsJob start at:" + printTime + ", prints: Hello Job-" + new Random().nextInt(100));

    }
}
