package com.qiniuyun.controller;

import com.qiniuyun.service.QiniuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author lzhstart
 * @create 2020/12/16 17:10
 */
@RestController
@CrossOrigin
public class UploadController {

    @Autowired
    QiniuServiceImpl qiniuServiceImpl;

    @PostMapping("/upload")
    @CrossOrigin
    public String uploadImg(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        //获取前端传来的文件名
        String filename = multipartFile.getOriginalFilename();
        //uuid生成唯一的文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //获取传文件的后缀
        String prefix = filename.substring(filename.lastIndexOf("."));
        //新的文件名
        String newFile = uuid+prefix;
        Path path = Paths.get("src/main/resources/image", newFile);
        Files.createDirectories(path.getParent());
        Files.createFile(path);
        //文件转换
        multipartFile.transferTo(path);
        File file = path.toFile();
        String url = qiniuServiceImpl.uploadFile(file, newFile);
        //如果文件存在则删除
        if(file.exists()) file.delete();
        //返回文件名
        return url;
    }
}
