package com.itheima.health.service;

import com.itheima.health.pojo.Menu;

import java.util.List;

/**
 * @author: admin
 * @createTime: 2020年11月05日
 * @Description：
 */
public interface MenueService {
    List<Menu> getMenue(Integer userId);
    //查询所有菜单信息
    List<Menu> findAllMenu();
}
