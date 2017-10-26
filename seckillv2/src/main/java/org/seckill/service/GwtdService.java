package org.seckill.service;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.GwtdProduct;
import org.seckill.entity.GwtdProject;
import org.seckill.entity.GwtdServer;
import org.seckill.entity.GwtdVersion;
import org.seckill.info.SerAndVerInfo;

import java.util.List;

/**
 * Created by shunge on 2017/5/25.
 */
public interface GwtdService {

    /**
     * 查询产品
     * @return
     */
    List<GwtdProduct> getProductList();

    /**
     * 查询项目
     * @param prodId 产品ID
     * @return
     */
    List<GwtdProject> getProjectById(long prodId);

    /**
     * 查询服务
     * @param projId 项目ID
     * @return
     */
    List<SerAndVerInfo> getServerById(long projId);

    /**
     * 查询版本
     * @param projId
     * @return
     */
    List<GwtdVersion> getVersionById(long projId);

    /**
     * 新增服务
     * @param gwtdServer
     * @return
     */
    int insertServer(GwtdServer gwtdServer);

    /**
     * 新增产品
     * @param gwtdProduct
     * @return
     */
    int insertProduct(GwtdProduct gwtdProduct);

    /**
     * 新增项目
     * @param gwtdProject
     * @return
     */
    int insertProject(GwtdProject gwtdProject);

    /**
     * 新增版本
     * @param gwtdVersion
     * @return
     */
    int insertVersion(GwtdVersion gwtdVersion);
}
