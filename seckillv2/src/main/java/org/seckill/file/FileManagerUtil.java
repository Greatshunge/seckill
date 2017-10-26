package org.seckill.file;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageServer;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shunge on 2017/5/10.
 */
//File manager util
public class FileManagerUtil {

    private static final Logger log = LoggerFactory.getLogger(FileManagerUtil.class);

    private final static int widthdist = 720;
    private final static int heightdist = 390;
    //test method

    public static void main(String[] args) throws Exception {
        String imageUrl = "C:\\Users\\sollee\\Desktop\\pic\\home_btn_3.png";
        upload(imageUrl);
    }

    //上传文件
    public static String[] upload(String imageUrl) throws Exception {
        //Check is null
        if (null == imageUrl || "".equals(imageUrl)) {
            return null;
        }
        //Get file
        File content = new File(imageUrl);
        //Get file format
        String fileSuffix = imageUrl.substring(imageUrl.lastIndexOf(".") + 1, imageUrl.length()).toUpperCase();
        //Get file name
        String fileName = imageUrl.substring(imageUrl.lastIndexOf("\\") + 1, imageUrl.lastIndexOf("."));

        FileInputStream fis = new FileInputStream(content);
        //File buffer
        byte[] file_buff = null;
        if (fis != null) {
            int len = fis.available();
            file_buff = new byte[len];
            fis.read(file_buff);
        }
        //FastDFS Object
        FastDFSFile file = new FastDFSFile(fileName, file_buff, fileSuffix);

        String[] uploadResults = FileManager.upload(file);
        if (null != uploadResults) {
            System.out.println(uploadResults[1]);
        }
        fis.close();
        return uploadResults;
    }

    /**
     *
     * @param fis
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String[] upload(InputStream fis, String fileName) throws Exception {
        //Check is null
        if (null == fis) {
            return null;
        }
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toUpperCase();
        //File buffer
        byte[] file_buff = null;
        if (fis != null) {
            int len = fis.available();
            file_buff = new byte[len];
            fis.read(file_buff);
        }
        //FastDFS Object
        FastDFSFile file = new FastDFSFile(fileName, file_buff, fileSuffix);

        String[] uploadResults = FileManager.upload(file);
        fis.close();
        return uploadResults;
    }

    /**
     * 720*390
     *
     * @param fis
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String[] compressUpload(InputStream fis, String fileName) {
        //Check is null
        if (null == fis) {
            return null;
        }
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toUpperCase();
        Image src;
        String[] uploadResults = null;
        ByteArrayOutputStream byteOutput = null;
        try {
            src = javax.imageio.ImageIO.read(fis);
            BufferedImage tag = new BufferedImage(widthdist, heightdist, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);

            byteOutput = new ByteArrayOutputStream();
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(byteOutput);
            encoder.encode(tag);
            //FastDFS Object
            FastDFSFile file = new FastDFSFile(fileName, byteOutput.toByteArray(), fileSuffix);

            uploadResults = FileManager.upload(file);
        } catch (Exception e) {
            log.error("上传压缩图片异常", e);
        } finally {
            try {

                if (byteOutput != null) {
                    byteOutput.close();
                }
            } catch (IOException e) {
                log.error("关闭文件流异常", e);
            }
        }
        return uploadResults;

    }

    //Download file(Is not used)
    public void getFile() throws Exception {
        FileInfo file = FileManager.getFile("group1", "M00/00/00/wKgDIFeNfUqAFV9RAAvqH_kipG8456.jpg");
        String sourceIpAddr = file.getSourceIpAddr();
        long size = file.getFileSize();
        System.out.println("ip:" + sourceIpAddr + ",size:" + size);
    }

    //Get store storages(Is not used)
    public void getStorageServer() throws Exception {
        StorageServer[] ss = FileManager.getStoreStorages("group1");

        for (int k = 0; k < ss.length; k++) {
            System.err.println(k + 1 + ". " + ss[k].getInetSocketAddress().getAddress().getHostAddress() + ":" + ss[k].getInetSocketAddress().getPort());
        }
    }

    //Get fetch storages(Is not used)
    public void getFetchStorages() throws Exception {
        ServerInfo[] servers = FileManager.getFetchStorages("group1", "M00/00/00/wKgBm1N1-CiANRLmAABygPyzdlw073.jpg");

        for (int k = 0; k < servers.length; k++) {
            System.err.println(k + 1 + ". " + servers[k].getIpAddr() + ":" + servers[k].getPort());
        }
    }
}
