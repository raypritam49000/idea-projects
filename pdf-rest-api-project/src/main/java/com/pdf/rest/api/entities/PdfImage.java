package com.pdf.rest.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PdfImage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String fileName;

    @Lob
    @Column(length = 1048576) // Modify the length to a suitable value based on your requirements
    private byte[] imageData;


    public PdfImage(String fileName, byte[] imageData) {
        this.fileName = fileName;
        this.imageData = imageData;
    }

}