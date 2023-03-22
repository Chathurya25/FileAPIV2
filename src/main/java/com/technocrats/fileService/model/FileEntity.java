package com.technocrats.fileService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_id", nullable = false)
    private Long id;

    @Lob
    private byte[] data;



    @Column(nullable = false)
    private String fileName;
    @Column(nullable = false)
    private String fileType;
    @Column(nullable = false, unique = true)
    private String owner;

//    @ManyToOne
//    @JoinColumn(name="p_id", nullable=false)
//    private Patient patient;
}
