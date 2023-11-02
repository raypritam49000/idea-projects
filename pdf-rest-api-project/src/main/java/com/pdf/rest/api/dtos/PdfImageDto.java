package com.pdf.rest.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PdfImageDto {
    private String id;

    private String fileName;
    private byte[] imageData;

    public PdfImageDto(String fileName, byte[] imageData) {
        this.fileName = fileName;
        this.imageData = imageData;
    }
}
