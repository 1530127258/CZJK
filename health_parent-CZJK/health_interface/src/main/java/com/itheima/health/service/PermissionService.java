package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.Permission;

import java.util.List;


public interface PermissionService {


    //分页查询
    PageResult<Permission> findPage(QueryPageBean queryPageBean);


    //新增
    boolean add(Permission permission);


    //删除
    boolean deleteById(int id)throws MyException;

    //编辑回显
    Permission findById(int id);


    //编辑修改提交
    boolean update(Permission permission);

    List<Permission> findAll();
}
