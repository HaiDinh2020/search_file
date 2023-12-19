package com.project3.elasticsearch.service;

import com.project3.elasticsearch.repo.ReadFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ReadPDF implements ReadFile {
    @Override
    public String readFile(MultipartFile file) {
        String text = null;
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            document.getClass();
            if (!document.isEncrypted()) {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper tStripper = new PDFTextStripper();
                text = tStripper.getText(document);
            }
        } catch (IOException e) {
            return  "there's an issue loading the PDF file " + e.getMessage();
        }
        return text;
    }
}
