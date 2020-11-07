package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.MenuDao;
import com.itheima.health.pojo.Menu;
import com.itheima.health.service.MenueService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: admin
 * @createTime: 2020年11月05日
 * @Description：
 */
@Service(interfaceClass = MenueService.class)
public class MenueServiceImpl implements MenueService {

    @Autowired
    private MenuDao menuDao;

    /**
     * 获取登录用户菜单
     * @param userId
     * @return
     */
    @Override
    public List<Menu> getMenue(Integer userId) {
        return menuDao.getMenue(userId);
    }
    //查询一级菜单
    @Override
    public List<Menu> findAllMenu() {
        List<Menu> parentMenu = menuDao.findFirstMenu();
        List<Menu> childrenMenu = menuDao.findSecondMenu();
        for (Menu menu1 : parentMenu) {
            Integer menu1Id = menu1.getId();
            for (Menu menu2 : childrenMenu) {
                if (menu2.getParentMenuId() == menu1Id) {
                    menu1.getChildren().add(menu2);
                }
            }
        }
        return parentMenu;
    }
}
