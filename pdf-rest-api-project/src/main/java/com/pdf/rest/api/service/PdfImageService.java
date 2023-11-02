package com.pdf.rest.api.service;

import com.pdf.rest.api.dtos.PdfImageDto;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public interface PdfImageService {
    public BufferedImage convertPdfToImage(InputStream inputStream) throws IOException;

    public byte[] convertImageToByteArray(BufferedImage image) throws IOException;

    public void saveImage(PdfImageDto pdfImageDto);

    public PdfImageDto getPdfImageById(String id);
}
