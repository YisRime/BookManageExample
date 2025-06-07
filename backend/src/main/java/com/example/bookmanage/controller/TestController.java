package com.example.bookmanage.controller;

import com.example.bookmanage.common.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    
    @GetMapping("/hello")
    public R hello() {
        Map<String, Object> data = new HashMap<>();
        data.put("message", "图书管理系统运行正常");
        data.put("time", LocalDateTime.now());
        data.put("version", "1.0.0");
        return R.success(data);
    }
    
    @GetMapping("/health")
    public R health() {
        return R.success("系统健康状态良好");
    }
}