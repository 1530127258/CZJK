package com.itheima.health.job;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.Utils.QiNiuUtils;
import com.itheima.health.service.OrderService;
import com.itheima.health.service.SetmealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: Eric
 * @since: 2020/10/26
 */
@Component
public class CleanImgJob {

    private static final Logger log = LoggerFactory.getLogger(CleanImgJob.class);

    @Reference
    private SetmealService setmealService;

    @Reference
    private OrderService orderService;

    //@Scheduled(fixedDelay = 1800000,initialDelay = 3000)
    public void cleanImg(){
        log.info("清理7牛上垃圾图片的任务开始执行...........");
        // 获取7牛上的图片
        List<String> imgIn7Niu = QiNiuUtils.listFile();
        log.debug("imgIn7Niu 有{}张图片要清理", imgIn7Niu.size());
        // 获取数据库套餐的图片
        List<String> imgInDb = setmealService.findImgs();
        log.debug("imgInDb 有{}张图片", imgInDb.size());
        // 7牛 - 数据库的
        imgIn7Niu.removeAll(imgInDb);
        log.debug("有{}张图片 需要删除", imgIn7Niu.size());
        // 要删除的
        try {
            log.info("开始删除七牛上的图片 {}", imgIn7Niu.size());
            QiNiuUtils.removeFiles(imgIn7Niu.toArray(new String[]{}));
            log.info("删除七牛上的图片 {}  成功===============", imgIn7Niu.size());
        } catch (Exception e) {
            //e.printStackTrace();
            log.error("清理垃圾图片出错了",e);
        }
    }


    /**
     * @author WJY
     * 定时清理 过期已到诊的用户
     * 这里是演示3分钟后
     * 他是先运行 在间隔10秒
     */
    @Scheduled(fixedDelay = 10000)
    public void delete(){

        orderService.delete();
        System.out.println("我是间隔10秒");

    }

    /**
     *
     * initialDelay: 启动时延迟多少毫秒后才执行
     * fixedDelay: 每间隔多长时间执行
     *
     * @author WJY
     * 定时清理 过期所有的用户
     * 这里是演示6分钟后
     * 这是 启动延时10秒再运行  间隔10秒在运行
     */
    @Scheduled(initialDelay = 10000 ,fixedDelay = 10000)
    public void deleteAll(){

        orderService.deleteAll();
        System.out.println("我是间隔5秒");
    }

}

