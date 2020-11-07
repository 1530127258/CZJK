package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Role;
import com.itheima.health.pojo.User;

import java.util.List;

public interface YhService {


   List<Role> findAll() ;

    Integer addYh(User user, Integer[] ids);

    //查询用户,并分页
    PageResult<User> fingYh(QueryPageBean queryPageBean);

    //编辑用户,之回显数据
    User findById(int id);

    //编辑用户之回显用户关联的角色信息
    List<Integer> findRoleIdsByUserId(int id);

    //编辑之提交
    void update(User user, Integer[] ids);

    void deleteYhById(int id);

    int findRole(int id);

    int fingUser(User user);
}
