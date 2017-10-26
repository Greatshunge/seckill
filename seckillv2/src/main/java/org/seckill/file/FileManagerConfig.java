package org.seckill.file;


import java.io.Serializable;

public interface FileManagerConfig extends Serializable{
	public static final String FILE_DEFAULT_WIDTH   = "120";  
    public static final String FILE_DEFAULT_HEIGHT  = "120";  
    public static final String FILE_DEFAULT_AUTHOR  = "";  
      
    public static final String PROTOCOL = "http://";  
    public static final String SEPARATOR = "/";  
      
    public static final String TRACKER_NGNIX_PORT   = ":80";  //Port
      
    public static final String CLIENT_CONFIG_FILE   = "fdfs_client.conf";  //Config File
}
