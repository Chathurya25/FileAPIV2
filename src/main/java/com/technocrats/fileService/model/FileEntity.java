package com.technocrats.fileService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file")
public class FileEntity {
    @Id
    private String id;
    @Lob
    @Column(name = "Data", length = 30000000)
    private byte[] data;
    @Column(name = "FileName" , nullable = false)
    private String fileName;
    @Column(name = "FileType" , nullable = false)
    private String fileType;
    @Column(name = "Owner" , nullable = false)
    private String owner;
    @Column(name = "Date")
    private Date date;
    @Column(name = "Message")
    private String message;



//    @ManyToOne
//    @JoinColumn(name="p_id", nullable=false)
//    private Patient patient;
}
