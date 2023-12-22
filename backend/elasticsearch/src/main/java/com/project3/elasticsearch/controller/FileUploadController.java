package com.project3.elasticsearch.controller;

//import com.project3.elasticsearch.repo.FileRepository;
import com.project3.elasticsearch.repo.ReadFile;
import com.project3.elasticsearch.service.ReadWord;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import com.project3.elasticsearch.service.FileUploadService;
import com.project3.elasticsearch.service.ReadPDF;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Objects;


@RestController
@RequestMapping("/api")
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    ReadPDF readPDF;

    @Autowired
    ReadWord readWord;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        if (file == null ) {
            return ResponseEntity.badRequest().body("Vui lòng tải lên một tệp tin");
        }
        // check type file
        try {
            InputStream inputStream = file.getInputStream();
            String extension = file.getOriginalFilename().split("\\.")[1];
            String textFile = "";

            if (isPDF(inputStream)) {
                textFile = readPDF.readFile(file);
            } else if (Objects.equals(extension, "docx")) {
                textFile = readWord.readFile(file);
            } else {
                return ResponseEntity.badRequest().body("Vui lòng tải lên một tệp tin pdf hoặc word");
            }

            String filePath = fileUploadService.saveFileToDisk(file);
            String fileName = file.getOriginalFilename();
            fileUploadService.saveToElasticsearch(textFile, fileName, filePath);
            return ResponseEntity.status(200).body("Upload file thành công");

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error checking file type");
        }
//        try {
//            String strings = fileUploadService.extractTextFromPdf(file);
//            String filePath = fileUploadService.uploadFile(file);
//            String fileName = file.getOriginalFilename();
//            fileUploadService.indexTextInElasticsearch(strings, fileName, filePath);
//            return ResponseEntity.ok("File đã được upload thành công: " + file.getOriginalFilename() );
//
//        } catch (Exception e) {
//            String message = "Không thể upload file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
//        }
    }

    private boolean isPDF(InputStream inputStream) {
        try {
            // Attempt to load the PDF document
            PDDocument.load(inputStream).close();
            return true;
        } catch (IOException e) {
            // Not a PDF or an error occurred
            return false;
        }
    }

    private boolean isWord(InputStream inputStream) {
        try {
            XWPFDocument document = new XWPFDocument(inputStream);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

//    @GetMapping("/allFile")
//    public ResponseEntity<List> getAllFile() {
//        List<File> result = fileRepository.findAll();
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }

//    @GetMapping("/search")
//    public ResponseEntity<Optional<Word>> searchWord (@RequestParam String keyword) {
//        Optional<Word> result = fileUploadService.searchWord(keyword);
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }
}
