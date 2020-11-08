package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.Role;
import com.itheima.health.pojo.User;
import com.itheima.health.service.YhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/Yh")
public class YhController {

    @Reference
    private YhService yhService;

    @Autowired
    private BCryptPasswordEncoder bCry;

    //添加用户
    @PostMapping("/add")
    public Result addYh(@RequestBody User user,Integer[] checkgroupIds) {

        user.setPassword(bCry.encode(user.getPassword().trim()));
       int i= yhService.fingUser(user);
       if (i>0){
           return new Result(false,"用户名已存在,请重新添加");
       }else {

           //调用方法传递参数,添加用户
           Integer YhId= yhService.addYh(user,checkgroupIds);
           return new Result(true,"添加用户成功");
       }






    }


    //查询用户,并分页
    @PostMapping("/findYh")
    public Result findYh(@RequestBody QueryPageBean queryPageBean){

        //传递参数,调用方法
       PageResult<User>  userPageResult= yhService.fingYh(queryPageBean);
       return new Result(true,"分页成功",userPageResult);
    }


    //编辑用户,之回显数据

    @GetMapping("/findById")
    public Result findById (int id){
        //调用方法,返回用户数据
        User user=yhService.findById(id);
        return new Result(true,"回显成功",user);
    }

    //编辑用户之回显用户关联的角色信息
    @GetMapping("/findRoleIdsByUserId")
    public Result findRoleIdsByUserId(int id){

        //调用方法,返回用户关联的角色id集合
       List<Integer> list = yhService.findRoleIdsByUserId(id);
       return new Result(true,"返回集合成功",list);

    }

    //编辑之提交
    @PostMapping("/update")
    public  Result update(@RequestBody User user,Integer[] checkgroupIds){


        yhService.update(user,checkgroupIds);
        return  new Result(true,"提交成功");
    }


    //用户 之删除用户
    @GetMapping("/deleteYhById")
    public Result deleteYhById(int id)throws MyException{
       int i= yhService.findRole(id);
       if (i>0){
           throw new MyException("请先删除关联角色");
       }
        yhService.deleteYhById(id);

        return new Result(true,"删除成功");
    }

    //查询所有角色
    @GetMapping("/findAll")
    public Result findAll ( ){
        List<Role> list =yhService.findAll();

        return    new Result(true,"成功",list);
    }

}
