package com.project3.elasticsearch.service;

import com.project3.elasticsearch.repo.ReadFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.IOException;
import java.io.InputStream;

@Component
public class ReadWord implements ReadFile {
    @Override
    public String readFile(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            XWPFDocument document = new XWPFDocument(inputStream);
            StringBuilder text = new StringBuilder();
            document.getParagraphs().forEach(paragraph -> {
                text.append(paragraph.getText());
                text.append("\n");
            });

            return text.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
