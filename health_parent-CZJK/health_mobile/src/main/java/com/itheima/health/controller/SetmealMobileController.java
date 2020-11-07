package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.itheima.health.Utils.QiNiuUtils;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: Eric
 * @since: 2020/10/28
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealMobileController {

    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 套餐列表
     * @return
     */
    @GetMapping("/getSetmeal")
    public Result getSetmeal(){


        Jedis jedis = jedisPool.getResource();
        String setmeal = jedis.get("setmeal");
        if (setmeal==null){
            //查询所有套餐
            List<Setmeal> list =setmealService.findAll();
            //处理图片路径
            list.forEach(s->{
                s.setImg(QiNiuUtils.DOMAIN+s.getImg());
            });
            String jsonString = JSON.toJSONString(list);
            jedisPool.getResource().set("setmeal",jsonString);
            jedis.close();
            return new Result(true,"上传成功",list);
        }else {
            List list = JSON.parseObject(setmeal, List.class);
            jedis.close();
            return new Result(true,"成功",list);
        }
    }

    /**
     * 套餐详情 
     */
    @GetMapping("/findDetailById")
    public Result findDetailById(int id){
        Jedis jedis = jedisPool.getResource();
        String s = jedis.get("setmealDetail{id}");

        if (s==null){
            Setmeal setmeal=setmealService.findDetailById(id);
            //获得图片路径
            setmeal.setImg( QiNiuUtils.DOMAIN+setmeal.getImg());
            String s1 = JSON.toJSONString(setmeal);
            jedisPool.getResource().set("setmealDetail"+id+"",s1);
            jedis.close();
            return new Result(true,"成功了o",setmeal);

        }else{
            Setmeal setmeal = JSON.parseObject(s, Setmeal.class);
            jedis.close();
            return new Result(true,"成功",setmeal);

        }
    }

    /**
     * 套餐详情
     */
    @GetMapping("/findDetailById2")
    public Result findDetailById2(int id){
        // 调用服务查询
        Setmeal s = setmealService.findDetailById2(id);
        // 图片的完整路径
        s.setImg(QiNiuUtils.DOMAIN+s.getImg());
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,s);
    }

    /**
     * 套餐详情
     */
    @GetMapping("/findDetailById3")
    public Result findDetailById3(int id){
        // 调用服务查询
        Setmeal s = setmealService.findDetailById3(id);
        // 图片的完整路径
        s.setImg(QiNiuUtils.DOMAIN+s.getImg());
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,s);
    }

    /**
     * 套餐基本信息
     */
    @GetMapping("/findById")
    public Result findById(int id){
        // 调用服务查询
        Setmeal s = setmealService.findById(id);
        // 图片的完整路径
        s.setImg(QiNiuUtils.DOMAIN+s.getImg());
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,s);
    }
}
