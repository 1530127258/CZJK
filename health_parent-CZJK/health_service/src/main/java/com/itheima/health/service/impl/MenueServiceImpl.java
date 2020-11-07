package com.itheima.health.service.imp;

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
}
