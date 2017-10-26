package org.seckill.file;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.File;

//File Manager class
public class FileManager implements FileManagerConfig {
	private static final long serialVersionUID = 1L;  
	  
    private static TrackerClient  trackerClient;  
    private static TrackerServer  trackerServer;  
    private static StorageServer  storageServer;  
    private static StorageClient  storageClient;  
  
    static { // Initialize Fast DFS Client configurations  
        try {  
            String classPath = new File(FileManager.class.getResource("/").getFile()).getCanonicalPath();  
              
            String fdfsClientConfigFilePath = classPath + File.separator + CLIENT_CONFIG_FILE;  
              
            ClientGlobal.init(fdfsClientConfigFilePath);  
              
            trackerClient = new TrackerClient();  
            trackerServer = trackerClient.getConnection();  
              
            storageClient = new StorageClient(trackerServer, storageServer);  
        } catch (Exception e) {}  
    }  
      
    //Upload file
    public static String[] upload(FastDFSFile file) throws Exception{  
          
        NameValuePair[] meta_list = new NameValuePair[3];  
        meta_list[0] = new NameValuePair("width", "120");  
        meta_list[1] = new NameValuePair("heigth", "120");  
        meta_list[2] = new NameValuePair("author", "pxg");  
          
        String[] uploadResults = null;  
        uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);  
        return uploadResults;  
    }  
    
    //Download file(Is not used)
    public static FileInfo getFile(String groupName, String remoteFileName) {  
        try {  
            return storageClient.get_file_info(groupName, remoteFileName);  
        } catch (Exception e) {  
        } 
        return null;  
    }  
    
    //Delete file(Is not used) 
    public static void deleteFile(String groupName, String remoteFileName) throws Exception {  
        storageClient.delete_file(groupName, remoteFileName);  
    }  
    
    //Get store storages(Is not used)
    public static StorageServer[] getStoreStorages(String groupName) throws Exception {  
        return trackerClient.getStoreStorages(trackerServer, groupName);  
    }  
    
    //Get fetch storages(Is not used)
    public static ServerInfo[] getFetchStorages(String groupName, String remoteFileName) throws Exception {  
        return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);  
    }  
}
