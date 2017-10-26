package org.seckill.web;

import org.seckill.entity.GwtdServer;
import org.seckill.entity.GwtdVersion;
import org.seckill.info.Exposer;
import org.seckill.info.GwtdResult;
import org.seckill.info.SeckillResult;
import org.seckill.service.FileService;
import org.seckill.service.GwtdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by shunge on 2017/5/10.
 */
@Controller
@RequestMapping(value = "/gwtd")
public class FileUploadController {

    public final static String HOST = "http://192.168.3.188/";
    public final static String uploadroute = "H:\\files\\";

    @Autowired
    private FileService fileService;
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private GwtdService gwtdService;


    //上传普通图片
    @ResponseBody
    @RequestMapping(value = "/file/upload.do", method = RequestMethod.POST)
    public SeckillResult<String> upload(MultipartHttpServletRequest multipartRequest, HttpServletResponse response, ModelMap model) {

        //获取文件ID(用于支持多文件上传)
        //String fileId = request.getParameter("Upload");
        // 得到上传的文件
        MultipartFile mFile = multipartRequest.getFile("file");
        // 得到上传的文件的文件名
        String filename = mFile.getOriginalFilename();

        InputStream inputStream = null;
        String dfsPath = null;
        try {
            inputStream = mFile.getInputStream();
            dfsPath = fileService.uploadFile(inputStream, filename);
            logger.info( "DFSPath:" + dfsPath);
        } catch (Exception e) {
            logger.error( "文件上传异常!", e);
            return new SeckillResult<String>(false,e.getMessage(),null);
        }

        try {
            inputStream.close();
        } catch (IOException e) {
            logger.error( "关闭流异常!", e);
            return new SeckillResult(false, e.getMessage());
        }

        return new SeckillResult<String>(true,null, HOST + dfsPath);
    }

    //上传普通文件
    @ResponseBody
    @RequestMapping(value = "/{projId}/file/uploadFile.do", method = RequestMethod.POST)
    public GwtdResult<String> uploadFile(MultipartHttpServletRequest multipartRequest, HttpServletResponse response, ModelMap model,@PathVariable("prodId")Long projId) {

        if (null == projId){
            logger.error("projId参数异常:"+projId);
            return new GwtdResult<String>(false,"参数异常!");
        }

        // 得到上传的文件
        MultipartFile mFile = multipartRequest.getFile("file");
        if(null == mFile){
            logger.error( "mFile参数异常:"+mFile);
            return new GwtdResult<String>(false,"参数异常!");
        }
        // 得到上传的文件的文件名
        String filename = mFile.getOriginalFilename();

        InputStream in = null;

        //上传文件
        try {
            OutputStream out = new FileOutputStream(uploadroute+filename);
            in = mFile.getInputStream();
            fileService.uploadVersionFile(in,out);
        } catch (Exception e) {
            logger.error( "文件上传异常!", e);
            return new GwtdResult<String>(false,e.getMessage());
        }

        //将记录保存
        GwtdVersion gwtdVersion = new GwtdVersion();
        gwtdVersion.setProjId(projId);
        gwtdVersion.setVerName(filename);
        gwtdVersion.setVerUrl(uploadroute);
        int result;
        try{
            result =  gwtdService.insertVersion(gwtdVersion);
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

