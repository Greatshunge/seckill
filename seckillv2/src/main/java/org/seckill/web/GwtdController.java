package org.seckill.web;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.GwtdProduct;
import org.seckill.entity.GwtdProject;
import org.seckill.entity.GwtdServer;
import org.seckill.entity.GwtdVersion;
import org.seckill.exception.GwtdException;
import org.seckill.info.GwtdResult;
import org.seckill.info.SerAndVerInfo;
import org.seckill.service.GwtdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shunge on 2017/5/25.
 */
@Controller
@RequestMapping("/gwtd")
public class GwtdController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private GwtdService gwtdService;

    @ResponseBody
    @RequestMapping(value = "/listProduct.do",method = RequestMethod.GET,produces = {"application/json;charset:utf-8"})
    public GwtdResult<GwtdProduct> listProduct(){
        List<GwtdProduct> result;
        try{
            result = gwtdService.getProductList();
            return new GwtdResult<GwtdProduct>(true,result);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new GwtdResult<GwtdProduct>(false,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{prodId}/listProject.do",method = RequestMethod.POST,produces = {"application/json;charset:utf-8"})
    public GwtdResult<GwtdProject> listProject(@PathVariable("prodId")Long prodId){
        List<GwtdProject> result;
        try{
            result = gwtdService.getProjectById(prodId);
            return new GwtdResult<GwtdProject>(true,result);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new GwtdResult<GwtdProject>(false,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{projId}/listServer.do",method = RequestMethod.POST,produces = {"application/json;charset:utf-8"})
    public GwtdResult<SerAndVerInfo> listServer(@PathVariable("projId") long projId){
        List<SerAndVerInfo> result;
        try{
            result = gwtdService.getServerById(projId);
            return new GwtdResult<SerAndVerInfo>(true,result);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new GwtdResult<SerAndVerInfo>(false,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{projId}/listVersion.do",method = RequestMethod.POST,produces = {"application/json;charset:utf-8"})
    public GwtdResult<GwtdVersion> listVersion(@PathVariable("projId") long projId){
        List<GwtdVersion> result;
        try{
            result = gwtdService.getVersionById(projId);
            return new GwtdResult<GwtdVersion>(true,result);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new GwtdResult<GwtdVersion>(false,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addServer.do",method = RequestMethod.POST,produces = {"application/json;charset:utf-8"})
    public GwtdResult addServer(@RequestBody GwtdServer gwtdServer){
        int result;
        try{
            result = gwtdService.insertServer(gwtdServer);
            if (result>0){
                return new GwtdResult(true);
            }
            return new GwtdResult(false,"添加失败");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new GwtdResult(false,"添加失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addProduct.do",method = RequestMethod.POST,produces = {"application/json;charset:utf-8"})
    public GwtdResult addProduct(@RequestBody GwtdProduct gwtdProduct){
        int result;
        try{
            result = gwtdService.insertProduct(gwtdProduct);
            if (result>0){
                return new GwtdResult(true);
            }
            return new GwtdResult(false,"添加失败");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new GwtdResult(false,"添加失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addProject.do",method = RequestMethod.POST,produces = {"application/json;charset:utf-8"})
    public GwtdResult addProject(@RequestBody GwtdProject gwtdProject){
        int result;
        try{
            result = gwtdService.insertProject(gwtdProject);
            if (result>0){
                return new GwtdResult(true);
            }
            return new GwtdResult(false,"添加失败");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new GwtdResult(false,"添加失败");
        }
    }
}
