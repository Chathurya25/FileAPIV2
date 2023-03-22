package com.technocrats.fileService.Service;

import com.technocrats.fileService.Repository.FileRepository;
import com.technocrats.fileService.model.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    public FileEntity saveFile(MultipartFile file){
        String filename = file.getOriginalFilename();
        try{
           FileEntity fileEntityToSave = new FileEntity();
           fileEntityToSave.setFileName(filename);
           fileEntityToSave.setFileType(file.getContentType());
           fileEntityToSave.setOwner("Chathurya");
           fileEntityToSave.setData(file.getBytes());
           return fileRepository.save(fileEntityToSave);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
