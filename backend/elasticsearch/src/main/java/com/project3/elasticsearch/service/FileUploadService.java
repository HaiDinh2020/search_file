package com.project3.elasticsearch.service;

//import com.project3.elasticsearch.entity.Word;
//import com.project3.elasticsearch.elasticsearchrepo.WordRepository;
import com.project3.elasticsearch.repo.FileRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.StringTokenizer;

@Service
public class FileUploadService {

    @Autowired
    FileRepository fileRepository;

    String pathname = "D:\\Hari_Marguire\\20231\\Project3\\FileUpload\\";

    public void uploadFile(MultipartFile file) throws IOException {
        try {
            String fileName = file.getOriginalFilename();
            String fileFullPath = pathname + fileName;
            String baseFileName = fileName.substring(0, fileName.lastIndexOf("."));

            fileRepository.save(new com.project3.elasticsearch.entity.File(baseFileName, fileFullPath));

            file.transferTo(new File(fileFullPath));
        } catch (Exception e) {
            // Log or handle the exception
            e.printStackTrace();
            throw new RuntimeException("Error uploading file: " + e.getMessage());
        }

    }


    public static String extractTextFromPdf(MultipartFile file) throws IOException {
        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);
        document.close();
        return text;
    }

    public void indexTextInElasticsearch(String text) throws IOException {

        String[] stopWords = {"is", "and", "the"};
        String list = "";
        for (String stopWord : stopWords) {
            text = text.replaceAll("\\b" + stopWord + "\\b", "");
        }

        String lowercasedText = text.toLowerCase();

        StringTokenizer tokenizer = new StringTokenizer(lowercasedText);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
//            Word indexWord = new Word(token, 1);
//            wordRepository.save(indexWord);
        }
    }

//    public Optional<Word> searchWord(String keyWord) {
//        return wordRepository.findById(keyWord);
//    }
}
