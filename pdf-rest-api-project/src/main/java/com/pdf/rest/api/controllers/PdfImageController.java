package com.pdf.rest.api.controllers;

import com.pdf.rest.api.dtos.PdfImageDto;
import com.pdf.rest.api.service.PdfImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/pdfs")
public class PdfImageController {

    private PdfImageService pdfImageService;

    @Autowired
    public void setPdfImageService(PdfImageService pdfImageService) {
        this.pdfImageService = pdfImageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPdfImage(@RequestParam("file") MultipartFile file) {
        try {

            // Convert PDF to image
            BufferedImage image = pdfImageService.convertPdfToImage(file.getInputStream());

            // Save the image to the database
            PdfImageDto pdfImageDto = new PdfImageDto();
            pdfImageDto.setFileName(file.getOriginalFilename());
            pdfImageDto.setImageData(pdfImageService.convertImageToByteArray(image));

            pdfImageService.saveImage(pdfImageDto);

            return ResponseEntity.ok("PDF image uploaded and saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload PDF image");
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getPdfImage(@PathVariable String id) {
        PdfImageDto pdfImage = pdfImageService.getPdfImageById(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG) // Set the appropriate content type
                .body(pdfImage.getImageData());
    }

}
