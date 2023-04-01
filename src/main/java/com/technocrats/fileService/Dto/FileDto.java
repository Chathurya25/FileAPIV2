package com.technocrats.fileService.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FileDto {
    private String id;
    private String fileName;
    private String fileType;
    private String owner;
    private Date date;
    private String message;

}
