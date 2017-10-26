package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by shunge on 2017/4/6.
 * 配置spring和junit整合,junit启动时加载springIOC容器
 * spring-test,junit
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spirng配置文件
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SeckillDaoTest {

    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void reduceNumber() throws Exception {
        Date killTime = new Date();
        int result = seckillDao.reduceNumber(1000L,killTime);
        System.out.println(result);
    }

    @Test
    public void queryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill);
        /**
         * Seckill{
         *          seckillId=1000,
         *          name='2000元秒杀iphone4',
         *          number=100,
         *          startTime=Sat Apr 01 00:00:00 CST 2017,
         *          endTime=Sun Apr 02 00:00:00 CST 2017,
         *          createTime=Sat Apr 01 15:35:47 CST 2017}
         */
    }

    @Test
    public void queryAll() throws Exception {
        List<Seckill> seckills = seckillDao.queryAll(0,100);
        for (Seckill seckill:seckills){
            System.out.println(seckill);
        }
    }

}