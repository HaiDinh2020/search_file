package com.project3.elasticsearch.controller;

import com.project3.elasticsearch.entity.Word;
import com.project3.elasticsearch.service.FileUploadService;
import com.project3.elasticsearch.service.WordServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/search")
public class WordController {

    @Autowired
    WordServices wordServices;

    @Autowired
    FileUploadService fileUploadService;

    ReactiveElasticsearchOperations operation;

    @GetMapping
    public Iterable<Word> getAll() {
        return wordServices.getAll();
    }

    @GetMapping("/pdf")
    public Iterable<Word> getPdfFile() {
        return  wordServices.getAllPdf();
    }

    @GetMapping("/word")
    public Iterable<Word> getWordFile() {
        return  wordServices.getAllWord();
    }

    @GetMapping("exac/{word}")
    public List<SearchHit<Word>> searchByPhrase(@PathVariable String word) {
        return wordServices.searchByPhrase(word);
    }

    @GetMapping("/multiword")
    public List<Word> searchMultiWord(
            @RequestParam("word1") String word1,
            @RequestParam("word2") String word2
    ) {
        return wordServices.combineAndSortLists(word1, word2);
    }
    @GetMapping("/{word}")
    public List<Word> searchByWord(@PathVariable String word) {
        return wordServices.searchByWord(word);
    }

    @DeleteMapping
    public void deleteAll() {
        wordServices.deleteAll();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteWord(@RequestBody() Word word) throws IOException {
        Long  deleteInES = wordServices.deleteByFileName(word.getFileName());
        boolean deletInDisk = fileUploadService.deleteInDiskByPath(word.getFileName());
        if(deleteInES > 0 && deletInDisk) {
            return ResponseEntity.status(200).body("xóa " + deleteInES + " file thành công");
        } else {
            return ResponseEntity.badRequest().body("xóa không thành công");
        }
    }
}
