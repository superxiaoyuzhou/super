package com.example.excel.controller;

import com.piter.excel.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/fileUpload")
    public Object upload(@RequestParam("file") MultipartFile file) throws Exception {
        return fileService.upload(file);
    }
    @RequestMapping("hello")
    public String hello(){
        return "hello";
    }
}
