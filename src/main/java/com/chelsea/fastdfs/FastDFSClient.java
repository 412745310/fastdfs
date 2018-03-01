package com.chelsea.fastdfs;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastDFSClient {

    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageServer storageServer = null;
    // 使用StorageClient1进行上传
    private MyStorageClient1 storageClient1 = null;
    private static final String GROUP_NAME = "nccgroup1";

    public FastDFSClient(String conf) throws Exception {
        // 获取classpath路径下配置文件"fdfs_client.conf"的路径
        // conf直接写相对于classpath的位置，不需要写classpath:
        String configPath = this.getClass().getClassLoader().getResource(conf).getFile();
        System.out.println(configPath);
        ClientGlobal.init(configPath);

        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = trackerClient.getStoreStorage(trackerServer, GROUP_NAME);
        System.out.println("ip : " + storageServer.getInetSocketAddress().getAddress().getHostAddress());
        System.out.println("port : " + storageServer.getSocket().getPort());
        storageClient1 = new MyStorageClient1(trackerServer, storageServer);
    }

    public String uploadFile(byte[] file_buff, String file_ext_name) throws Exception {
        String fileId = storageClient1.upload_file1(file_buff, file_ext_name, null);
        return fileId;
    }

    public byte[] downloadFile(String fileId) throws Exception {
//        int errorno = storageClient1.download_file1(fileId, "C:/Users/Administrator/Desktop/1.txt");
//        System.out.println(errorno);
        return storageClient1.download_file1_http(fileId);
    }

}
