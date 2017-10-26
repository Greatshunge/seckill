package org.seckill.InputTest;

import org.junit.Test;

import java.io.*;

/**
 * Created by shunge on 2017/5/19.
 */
public class ReadAndWriteTest {

    @Test
    public void createFile(){
        File file = new File("H:"+File.separator+"test");
        if (!file.exists()){
            file.mkdir();
            System.out.println("创建文件成功！");
        }else{
            System.out.println("文件已存在！");
        }
    }

    @Test
    public void createNewFile() throws IOException {
        File file = new File("H:"+File.separator+"test"+File.separator+"test1.txt");
        if(!file.exists()){
            file.createNewFile();
            System.out.println("创建文本成功！");
        }else{
            System.out.println("创建文本失败！");
        }
    }

    @Test
    public void writeTest() throws IOException {
        File file = new File("H:"+File.separator+"test"+File.separator+"test1.txt");
        FileOutputStream fos = new FileOutputStream(file);
        String temp = "hello world";
        byte[] sss = temp.getBytes();
        fos.write(sss);
        System.out.println("写入成功！");
        fos.close();
    }

    @Test
    public void readTest() throws IOException{
        File file = new File("H:"+File.separator+"test"+File.separator+"test1.txt");
        FileInputStream fis = new FileInputStream(file);
        fis.read();
    }
}
