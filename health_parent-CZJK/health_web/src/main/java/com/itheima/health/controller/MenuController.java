package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Menu;
import com.itheima.health.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author G-kong
 * @date 2020/11/6 12:09
 */

@RestController
@RequestMapping("/menus")
public class MenuController {

    @Reference
    private MenuService menuService;


    /**
     * 获取菜单数据
     * @return
     */
    @GetMapping("/getMenu")
    public Result getMenu() {
        // 调用业务服务
        List<Menu> list = menuService.getMenu();
        Integer size = list.size();
        Map<String, Object> map = new HashMap<>();
        map.put("data",list);
        map.put("size",size);
        // 封装到Result对象返回
        return new Result(true, MessageConstant.GET_MENU_SUCCESS,map);
    }


    /**
     * 分页条件查询
     * @param queryPageBean
     * @return
     */
    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        // 调用服务分页查询
        PageResult<Menu> pageResult = menuService.findPage(queryPageBean);
        // 返回给页面
        /**
         * {
         *     flag
         *     message
         *     data: {
         *         total
         *         rows
         *     }
         * }
         */
        return pageResult;
    }


    /**
     * 获取父菜单
     * @return
     */
    @GetMapping("/findParentAll")
    public Result findParentAll() {
        List<Menu> parentList = menuService.findParentAll();
        return new Result(true,"获取父菜单成功!",parentList);
    }


    /**
     * 添加子菜单
     * @param menu
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Menu menu) {
        // 调用业务服务
        menuService.add(menu);
        return new Result(true,"添加菜单成功!");
    }


    /**
     * 根据id删除菜单
     * @param id
     * @return
     */
    @PostMapping("/deleteById")
    public Result deleteById(int id) {
        // 调用服务业务
        menuService.deleteById(id);
        return new Result(true,"删除菜单成功!");
    }


    /**
     * 根据id查询菜单
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public Result findById(int id) {
        // 调用业务服务
        Menu menu = menuService.findById(id);
        return new Result(true,"根据id查询菜单成功",menu);
    }


    /**
     * 编辑菜单
     * @param menu
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Menu menu) {
        // 调用业务服务
        menuService.update(menu);
        return new Result(true,"修改菜单成功!");
    }

}
