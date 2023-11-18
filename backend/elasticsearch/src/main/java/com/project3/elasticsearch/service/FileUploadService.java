package com.project3.elasticsearch.service;

import co.elastic.clients.elasticsearch.core.IndexRequest;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileUploadService {

    // save file in forder D:\Hari_Marguire\20231\Project3\search-file\FileUpload\\
    public void uploadFile(MultipartFile file) throws IOException {
        file.transferTo(new File("D:\\Hari_Marguire\\20231\\Project3\\search-file\\FileUpload\\"+file.getOriginalFilename()));
    }

    // read pdf
    public static String extractTextFromPdf(MultipartFile file) throws IOException {
        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);
        document.close();
        return text;
    }

//    public void indexTextInElasticsearch(String text) throws IOException {
//        IndexRequest request = new IndexRequest("your_index_name");
//        Map<String, Object> jsonMap = new HashMap<>();
//        jsonMap.put("content", text);
//        request.source(jsonMap);
//
//        elasticsearchClient.index(request, RequestOptions.DEFAULT);
//    }
}
