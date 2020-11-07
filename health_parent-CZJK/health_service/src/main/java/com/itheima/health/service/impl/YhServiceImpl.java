package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.YhDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.Role;
import com.itheima.health.pojo.User;
import com.itheima.health.service.YhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service(interfaceClass = YhService.class)
public class YhServiceImpl implements YhService {

    @Autowired
    private YhDao yhDao;

    @Override
    public List<Role> findAll() {

        return yhDao.findAll();
    }

    //添加用户
    @Override
    public Integer addYh(User user, Integer[] ids) {


        yhDao.addYh(user);

        //获取新添加得用户ID
        Integer YhId = user.getId();
        //判断用户下角色,关联用户和角色中间表
        if (ids!=null){
            for (Integer id : ids) {
                //调用方法,关联用户和角色
                yhDao.addUserRole(YhId,id);
            }
        }

        return YhId;
    }


    //查询用户,并分页
    @Override
    public PageResult<User> fingYh(QueryPageBean queryPageBean) {
        //传递参数,插件分页
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //判断是否有条件
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        //调用方法,传递条件
        Page<User> page=yhDao.fingYh(queryPageBean.getQueryString());
        PageResult<User> pageresult=new PageResult<>(page.getTotal(),page.getResult());
        return pageresult;
    }

    //编辑用户,之回显数据
    @Override
    public User findById(int id) {
        User user =yhDao.findById(id);
        return user;
    }



    //编辑用户之回显用户关联的角色信息
    @Override
    public List<Integer> findRoleIdsByUserId(int id) {
        List<Integer> list=yhDao.findRoleIdsByUserId(id);
        return list;
    }

    //编辑之提交
    @Override
    @Transactional
    public void update(User user, Integer[] ids) {

        //更新界面信息
        yhDao.update(user);
        //删除原有角色信息id
       yhDao.deleteRoleIds(user.getId());
        //添加绑定id
        Integer YhId = user.getId();
        //判断用户下角色,关联用户和角色中间表
        if (ids!=null){
            for (Integer id : ids) {
                //调用方法,关联用户和角色
                yhDao.addUserRole(YhId,id);
            }
        }

    }

    //删除角色
    @Override
    public void deleteYhById(int id) {
        //删除用户和角色的关系
        yhDao.deleteRoleIds(id);
        //删除角色
        yhDao.deleteYhById(id);
    }

    @Override
    public int findRole(int id) {

        return yhDao.findRole(id);
    }

    @Override
    public int fingUser(User user) {
        //查询,如果用户名一样,用户存在
        String username = user.getUsername();
        Integer integer=yhDao.fingUser(username);
        return  integer;

    }


}
