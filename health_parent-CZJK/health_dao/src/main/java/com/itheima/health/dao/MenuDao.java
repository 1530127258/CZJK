package com.itheima.health.dao;

import com.itheima.health.pojo.Menu;

import java.util.List;

/**
 * @author: admin
 * @createTime: 2020年11月05日
 * @Description：
 */
public interface MenuDao {
    List<Menu> getMenue(Integer userId);
    //查询一级菜单
    List<Menu> findFirstMenu();

    //查询二级菜单
    List<Menu> findSecondMenu();
}
