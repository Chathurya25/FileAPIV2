package com.technocrats.fileService.Repository;

import com.technocrats.fileService.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository <FileEntity,String> {
    public List<FileEntity> findAllByOwner(String owner);
}
