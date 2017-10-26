package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.GwtdProduct;
import org.seckill.entity.GwtdProject;
import org.seckill.entity.GwtdServer;
import org.seckill.entity.GwtdVersion;
import org.seckill.info.SerAndVerInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by shunge on 2017/5/24.
 */
public interface GwtdDao {

    /**
     * 查询产品
     * @return
     */
    List<GwtdProduct> queryByProduct();

    /**
     * 根据产品ID查询项目
     * @param prodId
     * @return
     */
    List<GwtdProject> queryByIdWithProject(@Param("prodId") long prodId);

    /**
     * 根据项目ID查询服务
     * @param projId
     * @return
     */
    List<SerAndVerInfo> queryByIdWithServer(@Param("projId")long projId);

    /**
     * 查询版本列表
     * @param projId
     * @return
     */
    List<GwtdVersion> queryByIdWithVersion(@Param("projId")long projId);

    /**
     * 新增服务
     * @param gwtdServer
     */
    int insertServer(GwtdServer gwtdServer);

    /**
     * 新增产品
     * @param gwtdProduct
     */
    int insertProduct(GwtdProduct gwtdProduct);

    /**
     * 新增项目
     * @param gwtdProject
     */
    int insertProject(GwtdProject gwtdProject);

    /**
     * 新增版本
     * @param gwtdVersion
     * @return
     */
    int insertVersion(GwtdVersion gwtdVersion);

}
