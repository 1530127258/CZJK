package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.Role;

import java.util.List;

/**
 * @Auther lxy
 * @Date
 */
public interface RoleService {
    //分页查询角色
    PageResult<Role> findPage(QueryPageBean queryPageBean);

    //根据id查询角色
    Role findById(int id);

    //根据角色id查询关联的权限id
    List<Integer> findRolePermissionIds(int id);



    //查询所有角色
    List<Role> findAll();


    //根据id查询勾选的菜单
    List<Integer> findMenuIds(int id);


    //添加角色
    void addRole(Integer[] permissionIds, Integer[] menuIds, Role role);

    //更新角色
    void updateRole(Integer[] permissionIds, Integer[] menuIds, Role role);

    //删除角色
    void deleteRoleById(int id)throws MyException;
}
