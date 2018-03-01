package com.chelsea.fastdfs;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/fdfs")
public class FdfsController {

    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        String fileId = "nccgroup1/M00/00/25/CgwMTFqYCZ-ANQ1CAAAADDJN7P8722.txt";
        String filename = "fdfs文件.txt";
        try {
            FastDFSClient client = new FastDFSClient("fdfs_client.conf");
            byte[] bytes = client.downloadFile(fileId);
            response.reset();
            response.setContentType("application/octet-stream");
            Boolean flag= request.getHeader("User-Agent").indexOf("like Gecko")>0;
            if (request.getHeader("User-Agent").toLowerCase().indexOf("msie") >0||flag){  
                filename = URLEncoder.encode(filename, "UTF-8");
            }else {  
                filename = new String(filename.replaceAll(" ", "").getBytes("UTF-8"), "ISO-8859-1");
            }
            // 用来弹出保存窗口 ，设置 为attachment
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + filename);
            response.getOutputStream().write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
