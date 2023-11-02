package com.pdf.rest.api.service.impl;

import com.pdf.rest.api.dtos.PdfImageDto;
import com.pdf.rest.api.entities.PdfImage;
import com.pdf.rest.api.repositories.PdfImageRepository;
import com.pdf.rest.api.service.PdfImageService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class PdfImageServiceImpl implements PdfImageService {

    private PdfImageRepository pdfImageRepository;
    private ModelMapper modelMapper;

    @Autowired
    public void setPdfImageRepository(PdfImageRepository pdfImageRepository) {
        this.pdfImageRepository = pdfImageRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveImage(PdfImageDto pdfImageDto) {
        PdfImage pdfImage = this.modelMapper.map(pdfImageDto, PdfImage.class);
        this.pdfImageRepository.save(pdfImage);
    }

    @Override
    public PdfImageDto getPdfImageById(String id) {
       return this.modelMapper.map(pdfImageRepository.findById(id).get(),PdfImageDto.class);
    }

    @Override
    public BufferedImage convertPdfToImage(InputStream inputStream) throws IOException {
        PDDocument document = PDDocument.load(inputStream);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        BufferedImage image = pdfRenderer.renderImageWithDPI(0, 300); // Render the first page with 300 DPI
        document.close();
        return image;
    }

    @Override
    public byte[] convertImageToByteArray(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }
}
