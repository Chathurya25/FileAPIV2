package com.technocrats.fileService.Repository;

import com.technocrats.fileService.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository <FileEntity,Long> {

}
