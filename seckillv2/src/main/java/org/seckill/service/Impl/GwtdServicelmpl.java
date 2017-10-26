package org.seckill.service.Impl;

import org.seckill.dao.GwtdDao;
import org.seckill.entity.GwtdProduct;
import org.seckill.entity.GwtdProject;
import org.seckill.entity.GwtdServer;
import org.seckill.entity.GwtdVersion;
import org.seckill.info.SerAndVerInfo;
import org.seckill.service.GwtdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shunge on 2017/5/25.
 */
@Service
public class GwtdServicelmpl implements GwtdService {

    @Resource
    private GwtdDao gwtdDao;

    public List<GwtdProduct> getProductList() {
        return gwtdDao.queryByProduct();
    }

    public List<GwtdProject> getProjectById(long prodId) {
        return gwtdDao.queryByIdWithProject(prodId);
    }

    public List<SerAndVerInfo> getServerById(long projId) {
        return gwtdDao.queryByIdWithServer(projId);
    }

    public List<GwtdVersion> getVersionById(long projId) {
        return gwtdDao.queryByIdWithVersion(projId);
    }

    public int insertServer(GwtdServer gwtdServer) {
        return gwtdDao.insertServer(gwtdServer);
    }

    public int insertProduct(GwtdProduct gwtdProduct) {
        return gwtdDao.insertProduct(gwtdProduct);
    }

    public int insertProject(GwtdProject gwtdProject) {
        return gwtdDao.insertProject(gwtdProject);
    }

    @Override
    public int insertVersion(GwtdVersion gwtdVersion) {
        return gwtdDao.insertVersion(gwtdVersion);
    }
}
