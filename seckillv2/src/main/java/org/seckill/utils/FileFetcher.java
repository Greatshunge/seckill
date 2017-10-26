/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.seckill.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author shawn
 * @copyright Halo
 * @datetime 2017-4-28 17:42:14
 * @version 1.0
 */
public class FileFetcher {
    
    private static final Logger logger = LoggerFactory.getLogger(FileFetcher.class);

    public static final int cache = 10 * 1024;

    public static final int fetche(final String url, final String saveFile) {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(new PoolingHttpClientConnectionManager()).build();

        HttpGet httpget = new HttpGet(url);
        FileOutputStream fileout = null;
        InputStream is = null;
        try {
            HttpResponse response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();

            fileout = new FileOutputStream(new File(saveFile));

            byte[] buffer = new byte[cache];
            int ch;
            while ((ch = is.read(buffer)) != -1) {
                fileout.write(buffer, 0, ch);
            }

            fileout.flush();
        } catch (IOException e) {
            logger.error("下载文件[" + url + "]失败！", e);
            return -1;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
            
            if (fileout != null) {
                try {
                    fileout.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }

        return 1;
    }

    public static void main(String[] args) {
        String source = "http://pic36.nipic.com/20131203/13879008_225108687000_2.jpg";
        String destination = "H:\\ceshi.jpg";
        FileFetcher.fetche(source, destination);
    }
}