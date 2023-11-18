package com.project3.elasticsearch.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class PdfService {
    public static String extractTextFromPdf(MultipartFile file) throws IOException {
        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);
        document.close();
        return text;
    }


    public void ReadPdf() throws FileNotFoundException {
        File file = new File("C:\\Users\\HP\\Downloads\\sample.pdf");
        FileInputStream fileInputStream = new FileInputStream(file);
    }

}
