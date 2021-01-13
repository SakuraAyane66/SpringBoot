package com.example.demo.admin.jkyYoushibi.controller;

import com.example.demo.admin.jkyYoushibi.domain.JkyYoushibi;
import com.example.demo.admin.jkyYoushibi.service.jkyYoushibiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CTL
 * <p>油石比测试controller层 </p>
 * 创建日期：2021-01-13 10:35
 */
@RestController
public class JkyYoushibiController {

    @Autowired
    private jkyYoushibiService service;

    //测试
    @GetMapping("/getYoushibi")
    public List<JkyYoushibi> test(){

        return service.getAll();
    }
}
