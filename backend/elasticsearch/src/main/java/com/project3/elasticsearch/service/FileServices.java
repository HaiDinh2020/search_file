package com.project3.elasticsearch.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServices {
    public static List<String> listFiles() {
        List<String> fileList = new ArrayList<>();

        try {
            Path directory = Paths.get("D:\\Hari_Marguire\\20231\\Project3\\FileUpload\\");

            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    fileList.add(file.toString());
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileList;
    }
}
