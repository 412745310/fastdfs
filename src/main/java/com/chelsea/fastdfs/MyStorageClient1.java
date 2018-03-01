package com.chelsea.fastdfs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerServer;

public class MyStorageClient1 extends StorageClient1 {

    private static final String port = "8888";

    public MyStorageClient1() {
        super();
    }

    public MyStorageClient1(TrackerServer trackerServer, StorageServer storageServer) {
        super(trackerServer, storageServer);
    }

    public byte[] download_file1_http(String fileId) {
        String ip = storageServer.getInetSocketAddress().getAddress().getHostAddress();
        String url = "http://" + ip + ":" + port + "/" + fileId;
        byte[] fileByte = null;
        try {
            URL httpUrl = new URL(url);
            URLConnection conn = httpUrl.openConnection();
            InputStream in = conn.getInputStream();
            fileByte = input2byte(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileByte;
    }

    public final byte[] input2byte(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[4096];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, buff.length)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

}
