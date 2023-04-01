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

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("getFiles/{owner}")
    public List<FileDto> get(@PathVariable String owner){
        //model.addAttribute("files",files);
        return fileService.getFiles(owner);
    }

    @DeleteMapping("deletebyId/{Id}")
    public boolean delete(@PathVariable String Id){
        //model.addAttribute("files",files);
        return fileService.deleteFile(Id);
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFileWithVariables(@PathVariable("fileId")String fileId) throws IOException {
        // Get the file data as a byte array
;
        Optional<FileEntity> retrievedFile = fileService.getFile(fileId);

        byte[] fileData = retrievedFile.get().getData();

        // Set the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"example.pdf\"");

        // Set the response body as a ByteArrayResource
        ByteArrayResource resource = new ByteArrayResource(fileData);

        // Add the string variables to the response headers
        headers.add("X-Owner", retrievedFile.get().getOwner());
        headers.add("X-Message", retrievedFile.get().getMessage());
        headers.add("X-FileName", retrievedFile.get().getFileName());
        headers.add("X-FileType", retrievedFile.get().getFileType());
        headers.add("X-Date", String.valueOf(retrievedFile.get().getDate()));
        // Return the response entity with the file data and headers
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileData.length)
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
       }
    }
