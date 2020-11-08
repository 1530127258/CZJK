package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Permission;

import java.util.List;

public interface PermissionDao {


    //分页查询
    Page<Permission> findBycondition(String queryString);


    //新增
    int add(Permission permission);


    //删除
    int deleteById(int id);


    //编辑回显
    Permission findById(int id);


    //编辑修改提交
    int update(Permission permission);

    //查所有
    List<Permission> findAll();


    //查看该id是否被角色使用
    int findCountByCheckItemId(int id);
}
