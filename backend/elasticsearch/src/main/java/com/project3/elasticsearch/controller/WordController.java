package com.project3.elasticsearch.controller;

import com.project3.elasticsearch.entity.Word;
import com.project3.elasticsearch.service.WordServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class WordController {

    @Autowired
    WordServices wordServices;

    @GetMapping
    public Iterable<Word> getAll() {
        return wordServices.getAll();
    }

    @GetMapping("exac/{word}")
    public List<Word> searchByPhrase(@PathVariable String word) {
        return wordServices.searchByPhrase(word);
    }


    @GetMapping("/{word}")
    public List<Word> searchByWord(@PathVariable String word) {
        return wordServices.searchByWord(word);
    }

    @DeleteMapping
    public void deleteAll() {
        wordServices.deleteAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteWord(@PathVariable("id") String id) {
        wordServices.deleteWordById(id);
    }

}
