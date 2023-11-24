package com.project3.elasticsearch.controller;

import com.project3.elasticsearch.entity.File;
//import com.project3.elasticsearch.repo.FileRepository;
import com.project3.elasticsearch.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;

//    @Autowired
//    FileRepository fileRepository;

//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
//
//        if (file == null ) {
//            return ResponseEntity.badRequest().body("Vui lòng tải lên một tệp tin");
//        }
//
//        try {
//            fileUploadService.uploadFile(file);
//
////            String text = fileUploadService.extractTextFromPdf(file);
//            // lưu vào elastichsearch
////           fileUploadService.indexTextInElasticsearch(text);
//            return ResponseEntity.ok("File đã được tải lên thành công" );
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi xử lý tệp tin");
//        }
//    }

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
