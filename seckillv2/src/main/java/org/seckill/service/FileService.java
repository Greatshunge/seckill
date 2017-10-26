package org.seckill.service;

import org.seckill.exception.CommonServiceException;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by shunge on 2017/5/10.
 */
public interface FileService {

    /**
     * 文件上传.
     *
     * @param is    文件输入流
     * @param fileName  文件名
     * @return 服务器文件路径
     * @throws CommonServiceException
     */
    String uploadFile(InputStream is, String fileName) throws CommonServiceException;

    void uploadVersionFile(InputStream in, OutputStream out) throws Exception;
}
