package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.service.OrderService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyJob {

    @Reference
    private OrderService orderService;

    public void delete() {

        //调用业务层
        orderService.delete();



    }
}
