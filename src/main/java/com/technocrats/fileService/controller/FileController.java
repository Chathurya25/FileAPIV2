package com.technocrats.fileService.controller;

import com.technocrats.fileService.Service.FileService;
import com.technocrats.fileService.model.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RestController
@RequestMapping("api/v1")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/uploadFiles")
    public FileEntity uploadMultipartFiles(@RequestParam("file")MultipartFile file){
        FileEntity savedfile =fileService.saveFile(file);
        return savedfile;
    }

}
