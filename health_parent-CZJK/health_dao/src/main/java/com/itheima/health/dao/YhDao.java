package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Role;
import com.itheima.health.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YhDao {
    void addYh(User user);
    //调用方法,关联用户和角色
    void addUserRole(@Param("user_id") Integer yhId, @Param("role_id") Integer id);


    //查询用户,并分页
    Page<User> fingYh(String queryString);

    //编辑用户,之回显数据
    User findById(int id);

    //编辑用户之回显用户关联的角色信息
    List<Integer> findRoleIdsByUserId(int id);

    //编辑更新界面信息
    void update(User user);

    //删除原有角色信息id
    void deleteRoleIds(Integer id);


    void deleteYhById(int id);

    List<Role> findAll();

    int findRole(int id);

    Integer fingUser(String username);
}
