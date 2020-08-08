package com.zzp.cloud.business.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
public class TestController {
    @GetMapping("/employee/advanced/hi")
    public String test1() {
        return "这个资源Admin不能看哦！";
    }

    @GetMapping("/ws/ep/test")
    public String test2() {
        return "只要登录了都能看到它！";
    }
}
