package com.lh.nailweb.controller;

import com.lh.nailweb.entity.BaseMsg;
import com.lh.nailweb.util.MsgUtils;
import com.lh.nailweb.util.SnowFlakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @auther: loneyfall
 * @date: 2019/9/19
 * @description: 文件相关接口
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${file.upload.dir}")
    private String dir;

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @PostMapping("/upload")
    public BaseMsg upload(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println("[上传文件]fileName:" + fileName + "-->" + size + "bytes");

        String saveName = snowFlakeUtil.nextId() + "@" + fileName;
        String filePath = dir + "/" + saveName;
        File localFile = new File(filePath);

        if (!localFile.getParentFile().exists()) {
            localFile.getParentFile().mkdir();
        }

        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
            return MsgUtils.error("文件上传失败！");
        }
        return MsgUtils.success(saveName);
    }

    @GetMapping("/download/{fileName:.+}")
    public BaseMsg download(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        String filePath = dir + "/" + fileName;
        File file = new File(filePath);

        if (file.exists()) {
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            OutputStream os = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                os.flush();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return MsgUtils.error("文件下载失败！");
    }
}
