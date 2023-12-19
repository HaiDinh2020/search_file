package com.project3.elasticsearch.repo;

import org.springframework.web.multipart.MultipartFile;

public interface ReadFile {
    String readFile(MultipartFile file);
}
