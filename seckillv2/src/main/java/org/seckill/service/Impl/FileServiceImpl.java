package org.seckill.service.Impl;

import org.seckill.exception.CommonServiceException;
import org.seckill.file.FileManagerUtil;
import org.seckill.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * Created by shunge on 2017/5/10.
 */
@Service
public class FileServiceImpl implements FileService{

    private static final Logger logger = LoggerFactory.getLogger(FileManagerUtil.class);

    public String uploadFile(InputStream is, String fileName) throws CommonServiceException {
        try {
            String[] dfsid = FileManagerUtil.upload(is, fileName);

            return URLEncoder.encode(dfsid[0]+"//"+dfsid[1], "utf-8");
        } catch (Exception e) {
            logger.error("文件上传失败！", e);
            throw new CommonServiceException( "文件上传失败！", e);
        }
    }

    @Override
    public void uploadVersionFile(InputStream in, OutputStream out) throws IOException{

        try {
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            logger.error( "文件上传异常!", e);
            throw new IOException("文件上传异常!");
        }finally {
            in.close();
            out.close();
        }
    }
}
