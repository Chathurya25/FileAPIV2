package com.technocrats.fileService.controller;

import com.technocrats.fileService.Dto.FileDto;
import com.technocrats.fileService.Dto.FileUploadRequestDto;
import com.technocrats.fileService.Service.FileService;
import com.technocrats.fileService.model.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RestController
@RequestMapping("api/v1")
public class FileController {

    @Autowired
    private FileService fileService;

    @PutMapping("/uploadDocument") // URL
    public boolean uploadFile(@RequestParam("file")MultipartFile file,
                              @RequestParam("message")String message,
                              @RequestParam("owner")String owner){
        return fileService.saveFile(file,message,owner);
    }

    @GetMapping("/")
    public List<FileDto> get(Model model){
        //model.addAttribute("files",files);
        return fileService.getFiles();
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileId){
        FileEntity file = fileService.getFile(fileId).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename-\""+file.getFileName()+"\"")
                .body(new ByteArrayResource(file.getData()));
    }

}
