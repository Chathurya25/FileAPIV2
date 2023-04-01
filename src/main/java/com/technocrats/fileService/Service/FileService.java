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

    public FileEntity saveFile(MultipartFile file){
        String filename = file.getOriginalFilename();
        try{
           FileEntity fileEntityToSave = new FileEntity();
           fileEntityToSave.setFileName(filename);
           fileEntityToSave.setFileType(file.getContentType());
           fileEntityToSave.setOwner(owner);
           fileEntityToSave.setData(file.getBytes());
           return fileRepository.save(fileEntityToSave);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
