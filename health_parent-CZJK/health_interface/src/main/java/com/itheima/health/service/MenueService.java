package com.itheima.health.service;

import com.itheima.health.pojo.Menu;
import com.itheima.health.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @author: admin
 * @createTime: 2020年11月05日
 * @Description：
 */
public interface MenueService {
    List<Menu> getMenue(Integer userId);
}
