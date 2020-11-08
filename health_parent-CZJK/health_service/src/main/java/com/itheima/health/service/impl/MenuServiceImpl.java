package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.MenuDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.Menu;
import com.itheima.health.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author G-kong
 * @date 2020/11/6 12:15
 */

@Service(interfaceClass = MenuService.class)
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    /**
     * 获取菜单数据
     *
     * @return
     */
    @Override
    public List<Menu> getMenu() {
        List<Menu> parentMenus = menuDao.getParentMenu();  // 获取父菜单
        List<Menu> childrenMenus = menuDao.getChildrenMenu(); // 获取子菜单
        if (parentMenus != null) {
            parentMenus.forEach(p -> {
                if (childrenMenus != null) {
                    childrenMenus.forEach(c -> {
                        if (p.getId() == c.getParentMenuId()) {
                            p.getChildren().add(c);
                        }
                    });
                }
            });
        }
        /*if (null != parentMenus) {
            for (Menu parentMenu : parentMenus) {
                if (null != childrenMenus) {
                    for (Menu childrenMenu : childrenMenus) {
                        if (parentMenu.getId() == childrenMenu.getParentMenuId()) {
                            parentMenu.getChildren().add(childrenMenu);
                        }
                    }
                }
            }
        }*/
        return parentMenus;
    }

    /**
     * 分页条件查询
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<Menu> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        if (!StringUtils.isEmpty(queryPageBean.getQueryString())) {
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        Page<Menu> page = menuDao.findPage(queryPageBean.getQueryString());
        PageResult<Menu> pageResult = new PageResult<Menu>(page.getTotal(), page.getResult());
        return pageResult;
    }

    /**
     * 获取父菜单
     *
     * @return
     */
    @Override
    public List<Menu> findParentAll() {
        return menuDao.getParentMenu();
    }

    /**
     * 添加菜单
     *
     * @param menu
     */
    @Override
    public void add(Menu menu) {
        if (menu.getPath().length() == 1) {
            menuDao.addParent(menu);
        } else {
            // 添加子菜单
            menuDao.addChildren(menu);
        }
    }

    /**
     * 根据id删除菜单
     *
     * @param menuId
     */
    @Override
    public void deleteById(int menuId) {
        int count = menuDao.findByParent(menuId);
        if (count > 0) {
            // 父菜单下有子菜单则不能删除
            throw new MyException("该父菜单已包含子菜单，不能删除");
        }
        menuDao.deleteById(menuId);
    }

    /**
     * 根据id查询菜单
     *
     * @param id
     * @return
     */
    @Override
    public Menu findById(int id) {
        return menuDao.findById(id);
    }

    /**
     * 编辑菜单
     *
     * @param menu
     */
    @Override
    public void update(Menu menu) {
        menuDao.update(menu);
    }
}
