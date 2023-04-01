package com.technocrats.fileService.Service;

import com.technocrats.fileService.Dto.FileDto;
import com.technocrats.fileService.Repository.FileRepository;
import com.technocrats.fileService.model.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    public boolean saveFile(MultipartFile file, String message, String owner ){
        try{
           // Get the file name from the file.
           String filename = file.getOriginalFilename();
           // Create an object of the file entity to save in the database.
           FileEntity fileEntityToSave = new FileEntity();
           // Populate the object with the data from the rest call.
            fileEntityToSave.setId(UUID.randomUUID().toString());
           fileEntityToSave.setFileName(filename);
           fileEntityToSave.setFileType(file.getContentType());
           fileEntityToSave.setOwner(owner);
           fileEntityToSave.setData(file.getBytes());
           fileEntityToSave.setDate(new Date());
           fileEntityToSave.setMessage(message);
           // Use the JPA repository to save the object as a record in the database
           FileEntity savedFile =  fileRepository.save(fileEntityToSave);
           if (savedFile == null){
               return false;
           }else {
               return true;
           }
        }catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public Optional<FileEntity> getFile(Long fileId) {
        return fileRepository.findById(fileId);
    }

    public List<FileDto>getFiles(){

        List<FileEntity> files = fileRepository.findAll();

        List<FileDto> fileDtoList = new ArrayList<>();

        files.forEach(fileEntity -> {
            fileDtoList.add(FileDto.builder()
                    .id(fileEntity.getId())
                    .fileType(fileEntity.getFileType())
                    .fileName(fileEntity.getFileName())
                    .owner(fileEntity.getOwner())
                    .build());
        });
        return fileDtoList;
    }


}
