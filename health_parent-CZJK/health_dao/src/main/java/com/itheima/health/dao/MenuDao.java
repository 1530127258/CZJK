package com.itheima.health.dao;

import com.github.pagehelper.Page;
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


    /*=============================菜单管理============================*/
    /**
     * 获取父菜单
     * @return
     */
    List<Menu> getParentMenu();

    /**
     * 获取子菜单
     * @return
     */
    List<Menu> getChildrenMenu();

    /**
     * 分页条件查询
     * @param
     * @return
     */
    Page<Menu> findPage(String queryString);



    Menu findById(int id);

    /**
     * 添加父菜单
     * @param menu
     */
    void addParent(Menu menu);

    /**
     * 添加子菜单
     * @param menu
     */
    void addChildren(Menu menu);

    /**
     * 编辑菜单
     * @param menu
     */
    void update(Menu menu);

    /**
     * 根据id删除菜单
     * @param menuId
     */
    void deleteById(int menuId);

    /**
     * 根据id判断父菜单是否包含子菜单
     * @param menuId
     * @return
     */
    int findByParent(int menuId);

}
