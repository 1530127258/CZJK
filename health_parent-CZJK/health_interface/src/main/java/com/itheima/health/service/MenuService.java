package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.Menu;

import java.util.List;

/**
 * @author G-kong
 * @date 2020/11/6 12:14
 */
public interface MenuService {

    /**
     * 获取菜单数据
     * @return
     */
    List<Menu> getMenu();

    /**
     * 分页条件查询
     * @param queryPageBean
     * @return
     */
    PageResult<Menu> findPage(QueryPageBean queryPageBean);

    /**
     * 获取父菜单
     * @return
     */
    List<Menu> findParentAll();

    /**
     * 添加子菜单
     * @param menu
     */
    void add(Menu menu);

    /**
     * 根据id删除紫菜单
     * @param menuId
     */
    void deleteById(int menuId) throws MyException;

    /**
     * 根据id查询菜单
     * @param id
     * @return
     */
    Menu findById(int id);

    /**
     * 编辑菜单
     * @param menu
     */
    void update(Menu menu);
}
