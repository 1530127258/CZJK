package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.BingTuAgeDao;
import com.itheima.health.service.BingTuAgeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

//注册
@Service(interfaceClass = BingTuAgeService.class)
public class BingTuAgeServiceImpl implements BingTuAgeService {

    //注入属性
    @Autowired
    private BingTuAgeDao bingTuAgeDao;

    @Override
    public List<Map<String, Object>> BingTu() {

        return bingTuAgeDao.BingTu();
    }

    @Override
    public List<Map<String, Object>> BingTuBySex() {
        return  bingTuAgeDao.BingTuBySex();
    }
}
