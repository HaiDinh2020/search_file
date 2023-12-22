package com.project3.elasticsearch.service;

//import com.project3.elasticsearch.entity.Word;
//import com.project3.elasticsearch.elasticsearchrepo.WordRepository;
import com.project3.elasticsearch.elasticsearchrepo.WordRepository;
import com.project3.elasticsearch.entity.Word;
import com.project3.elasticsearch.repo.FileRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadService implements FileRepository{

//    @Autowired
//    FileRepository fileRepository;
    @Autowired
    WordRepository wordRepository;
    private final Path root = Paths.get("uploads");
    String pathname = "D:\\Hari_Marguire\\20231\\Project3\\FileUpload\\";

    public String saveFileToDisk(MultipartFile file) throws IOException {
        try {
            String fileName = file.getOriginalFilename();
            String fileFullPath = pathname + fileName;
            String baseFileName = fileName.substring(0, fileName.lastIndexOf("."));

            file.transferTo(new File(fileFullPath));
            return fileFullPath;
        } catch (Exception e) {
            // Log or handle the exception
            e.printStackTrace();
            throw new RuntimeException("Error uploading file: " + e.getMessage());
        }

    }

    public String extractTextFromPdf(MultipartFile file) throws IOException {
        String text ="";
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            document.getClass();
            if (!document.isEncrypted()) {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper tStripper = new PDFTextStripper();
                String pdfFileInText = tStripper.getText(document);
                text = pdfFileInText;
            }
        } catch (IOException e) {
            return  "there's an issue loading the PDF file " + e.getMessage();
        }
        return text;
    }
    public void saveToElasticsearch(String strings, String fileName, String filePath) throws IOException {
        Word newword = new Word(strings, fileName, filePath);
        wordRepository.save(newword);
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteAll() {

    }
    public boolean deleteInDiskByPath(String fileName) {
        try {
            File fileToDelete = new File(pathname+fileName);

            if (fileToDelete.exists()) {
                if (fileToDelete.delete()) {
                    System.out.println("File deleted successfully: " + fileName);
                    return true;
                } else {
                    System.out.println("Failed to delete the file: " + fileName);
                    return false;
                }
            } else {
                System.out.println("File not found: " + fileName);
                return false;
            }
        } catch (Exception e) {
            // Log or handle the exception
            e.printStackTrace();
            throw new RuntimeException("Error deleting file: " + e.getMessage());
        }
    }
}
