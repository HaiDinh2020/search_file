package com.project3.elasticsearch.controller;

import com.project3.elasticsearch.service.FileUploadService;
import com.project3.elasticsearch.service.PdfService;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        if (file == null ) {
            return ResponseEntity.badRequest().body("Vui lòng tải lên một tệp tin");
        }

        try {
            fileUploadService.uploadFile(file);

            String text = fileUploadService.extractTextFromPdf(file);
            // lưu vào elastichsearch

            return ResponseEntity.ok("File đã được tải lên thành công"+ text  );
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi xử lý tệp tin");
        }
    }
}