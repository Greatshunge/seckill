package org.seckill.file;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by shunge on 2017/5/15.
 */
public class TestFileManager {

    @Test
    public void upload() throws Exception {
        String imageUrl = "C:\\123.jpg";
        FileManagerUtil.upload(imageUrl);
    }
}
