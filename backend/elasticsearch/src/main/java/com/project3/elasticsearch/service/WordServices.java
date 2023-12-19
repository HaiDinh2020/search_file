package com.project3.elasticsearch.service;

import com.project3.elasticsearch.elasticsearchrepo.WordRepository;
import com.project3.elasticsearch.entity.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordServices  {

    @Autowired
    WordRepository wordRepository;

    public List<Word> searchByPhrase(String word) {
        return wordRepository.customFindByExacContent(word);
    }

    public List<Word> searchByWord(String word) {
        return wordRepository.customFindByContent(word);
    }

    public Iterable<Word> getAll() {
        return wordRepository.findAll();
    }

    public void deleteAll() {
        wordRepository.deleteAll();
    }

    public void deleteWordById (String id) {
        wordRepository.deleteById(id);
    }

}
