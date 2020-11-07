package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.entity.Result;
import com.itheima.health.service.BingTuAgeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/report")
@RestController
public class BingTuAge {


    //订阅
    @Reference
    private BingTuAgeService bingTuAgeService;


    @RequestMapping("/getmemberageReport")
    public Result BingTu(){
        //没有返回的类接收,自己创建一个map集合接收数据库返回的name和值,的集合
        // 调用服务查询
        // 套餐数量
        List<Map<String,Object>> list=bingTuAgeService.BingTu();

        //创建返回值类型map
        Map<String,Object>map=new HashMap<>();
        //封装套餐数量
        map.put("memberAgeCount",list);

        // 套餐名称集合
        ArrayList<String> memberAge = new ArrayList<>();

        //遍历集合 获得年龄段姓名 // 抽取套餐名称
        for (Map<String, Object> stringObjectMap : list) {
            String name = stringObjectMap.get("name").toString();
            memberAge.add(name);
        }
        map.put("memberAge",memberAge);
        return new Result(true,"查询显示数据成功",map);


    }



    //饼图  年龄
    @RequestMapping("/getSetMemberSexReport")
    public Result BingTuBySex(){
        List<Map<String,Object>> list=bingTuAgeService.BingTuBySex();
        //创建MAP集合,返回数据
        Map<String,Object> map=new HashMap<>();
        map.put("memberSexCount",list);
        //创建性别集合
        ArrayList<String> arrayList = new ArrayList<>();
        //遍历集合获取 性别
        for (Map<String, Object> stringObjectMap : list) {
            String sex = stringObjectMap.get("name").toString();
            arrayList.add(sex);

        }
        map.put("memberSex",arrayList);
        return new Result(true,"查询显示数据成功",map);

    }

}
