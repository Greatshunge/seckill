package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.GwtdProduct;
import org.seckill.entity.GwtdProject;
import org.seckill.entity.GwtdServer;
import org.seckill.entity.GwtdVersion;
import org.seckill.info.SerAndVerInfo;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by shunge on 2017/5/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spirng配置文件
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class GwtdDaoTest {

    @Resource
    private GwtdDao gwtdDao;

    @Test
    public void queryByProduct() throws Exception {

        List<GwtdProduct> lists = gwtdDao.queryByProduct();
        if (lists!=null&&lists.size()>0){
            for (GwtdProduct list:lists){
                System.out.println(list);
            }
        }
    }

    @Test
    public void queryByIdWithProject() throws Exception {

        long prodId = 2;
        List<GwtdProject> lists = gwtdDao.queryByIdWithProject(prodId);
        if (lists!=null&&lists.size()>0){
            for (GwtdProject list:lists){
                System.out.println(list);
            }
        }
    }

    @Test
    public void queryByIdWithServer() throws Exception {
        long projId = 1;
        List<SerAndVerInfo> lists = gwtdDao.queryByIdWithServer(projId);
        if (lists!=null&&lists.size()>0){
            for (SerAndVerInfo list:lists){
                System.out.println(list);
            }
        }
    }

    @Test
    public void queryByIdWithVersion() throws Exception {
        long projId = 1;
        List<GwtdVersion> lists = gwtdDao.queryByIdWithVersion(projId);
        if (lists!=null&&lists.size()>0){
            for (GwtdVersion list:lists){
                System.out.println(list);
            }
        }
    }

    @Test
    public void insertServer() throws Exception {
        GwtdServer gwtdServer = new GwtdServer();
        gwtdServer.setProjId(3);
        gwtdServer.setServName("server3");
        gwtdServer.setServIp("192.168.3.177");
        gwtdServer.setServVerPort(8080);
        gwtdServer.setVerId(1);
        gwtdServer.setServDesc("没事");

        int result = gwtdDao.insertServer(gwtdServer);
        if (result>0){
            System.out.println("添加服务成功："+gwtdServer.toString());
        }else{
            System.out.println("添加失败!");
        }
    }

}