package com.itheima.health.controller;

import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author: Eric
 * @since: 2020/10/31
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 获取登陆用户名
     * @return
     */
    @GetMapping("/getUsername")
    public Result getUsername(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("登陆的用户名:" + user.getUsername());
        return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,user.getUsername());
    }

    @RequestMapping("/loginSuccess")
    public Result loginSuccess() {
        //返回
        return new Result(true, "登录成功");
    }
    @RequestMapping("/loginFail")
    public Result loginFail() {
        //返回
        return new Result(false, "用户名或密码错误");
    }
}
