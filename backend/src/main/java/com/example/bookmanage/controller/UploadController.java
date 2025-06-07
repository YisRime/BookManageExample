package com.example.bookmanage.controller;

import com.example.bookmanage.common.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {
    
    @Value("${file.upload.path}")
    private String uploadPath;
    
    @PostMapping("/image")
    public R uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return R.error("上传文件为空");
        }
        
        try {
            // 获取原始文件名
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            
            // 生成新文件名
            String fileName = UUID.randomUUID().toString() + suffix;
            
            // 创建日期目录
            String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            String dirPath = uploadPath + datePath + "/";
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            // 保存文件
            String filePath = dirPath + fileName;
            file.transferTo(new File(filePath));
            
            // 返回访问URL
            String fileUrl = "/images/" + datePath + "/" + fileName;
            return R.success(fileUrl, "上传成功");
            
        } catch (IOException e) {
            e.printStackTrace();
            return R.error("上传失败");
        }
    }
}