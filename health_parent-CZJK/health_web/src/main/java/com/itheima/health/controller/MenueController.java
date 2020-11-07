package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Menu;
import com.itheima.health.service.MenueService;
import com.itheima.health.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: admin
 * @createTime: 2020年10月31日
 * @Description：
 */

@RestController
@RequestMapping("/menu")
public class MenueController {

    @Reference
    private MenueService menueService;
    @Reference
    private UserService userService;

    /**
     *获取登录用户菜单
     * @param username
     * @return
     */
    @GetMapping("/getMenu")
    public Result getMenue(String username){
        try {
            //根据用户名查询用户
            com.itheima.health.pojo.User user = userService.findUserByUsername(username);
            //获取用户id
            Integer userId = user.getId();
            //获取菜单列表
            List<Menu> menuList = menueService.getMenue(userId);
            return new Result(true,MessageConstant.GET_MENU_SUCCESS,menuList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.GET_MENU_FAIL);
        }
    }


    //查询所有菜单信息
    @GetMapping("/findAllMenu")
    public Result findAllMenu() {
        List<Menu> data2 = menueService.findAllMenu();
        return new Result(true, "成功", data2);
    }

}
