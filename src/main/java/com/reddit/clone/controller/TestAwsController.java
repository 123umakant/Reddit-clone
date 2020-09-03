package com.reddit.clone.controller;

import com.reddit.clone.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/uploadfile")
public class TestAwsController {

    @Autowired
    private FileService fileService;

    @PostMapping
    public String uploadFile(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        return fileService.upLoadFile(multipartFile);
    }
}
