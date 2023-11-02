package com.pdf.rest.api.repositories;

import com.pdf.rest.api.entities.PdfImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfImageRepository extends JpaRepository<PdfImage, String> {
}
