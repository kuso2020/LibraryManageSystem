package org.librarySystem.controller;


import cn.hutool.core.io.FileUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.librarySystem.common.Result;
import org.librarySystem.exception.CustomException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/files")
public class FileController {
    private final String FILE_DIRECTORY  = System.getProperty("user.dir") + "/files/";
    @PostMapping("/upload")
    public Result upload(MultipartFile file){
        String originalFileName = file.getOriginalFilename();
        String fileName = System.currentTimeMillis() + "_"+ originalFileName;
        String savePath = FILE_DIRECTORY + fileName;
        String url = "http://localhost:8080/files/download/" + fileName;
        //判断路径是否存在
        if (!FileUtil.isDirectory(FILE_DIRECTORY )){
            FileUtil.mkdir(FILE_DIRECTORY );
        }
            //保存文件
        try{
            FileUtil.writeBytes(file.getBytes(), savePath);
        }catch (IOException e){
            e.printStackTrace();
            return Result.error("500", "上传失败");
        }
        return Result.success(url);
    }

    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response){
        String filePath = FILE_DIRECTORY + fileName;
        try{
            //下载文件

            response.setHeader("Content-Disposition", "attachment;filename=" +
                    URLEncoder.encode(fileName, StandardCharsets.UTF_8));//中文文件名cookie乱码问题
            response.setContentType("application/octet-stream");
            OutputStream os = response.getOutputStream();
            os.write(FileUtil.readBytes(filePath));
            os.flush();
            os.close();
        }catch (IOException e){
            e.printStackTrace();
            throw new CustomException("500", "下载失败");
        }

    }


}
