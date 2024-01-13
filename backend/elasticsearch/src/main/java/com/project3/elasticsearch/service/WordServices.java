package com.project3.elasticsearch.service;

import com.project3.elasticsearch.elasticsearchrepo.WordRepository;
import com.project3.elasticsearch.entity.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WordServices  {

    @Autowired
    WordRepository wordRepository;

    public List<SearchHit<Word>> searchByPhrase(String word) {
        return wordRepository.customFindByExacContent(word);
    }

    public List<Word> searchByWord(String word) {
        return wordRepository.customFindByContent(word);
    }

    public List<SearchHit<Word>>  searchMultiWord(String word1, String word2) {
        return wordRepository.findByContentWithScore(word1, word2);
    }

    public Iterable<Word> getAll() {
        return wordRepository.findAll();
    }

    public Iterable<Word> getAllPdf() {
        return wordRepository.findByFileNameEndingWith(".pdf");
    }

    public Iterable<Word> getAllWord() {
        return wordRepository.findByFileNameEndingWith(".docx");
    }
    public void deleteAll() {
        wordRepository.deleteAll();
    }

    public Long deleteByFileName(String id) {
        return wordRepository.deleteByFileName(id);
    }

    public List<Word> combineAndSortLists(String word1, String word2) {

        List<SearchHit<Word>> list1 = wordRepository.customFindByExacContent(word1);
        List<SearchHit<Word>> list2 = wordRepository.customFindByExacContent(word2);

        List<SearchHit<Word>> combinedList = new ArrayList<>(list1.size() + list2.size());
        combinedList.addAll(list1);
        combinedList.addAll(list2);

        Collections.sort(combinedList, (a, b) -> Double.compare(b.getScore(), a.getScore()));
        Set<String> uniqueIds = new HashSet<>();
        List<SearchHit<Word>> result = new ArrayList<>();

        for (SearchHit<Word> myData : combinedList) {
            if (uniqueIds.add(myData.getId())) {
                result.add(myData);
            }
        }

        List<Word> contentFieldsList = result.stream()
                .map(wordData -> {
                    Word contentFields = new Word();
                    contentFields.setId(wordData.getId());
                    contentFields.setContent(wordData.getContent().getContent());
                    contentFields.setFileName(wordData.getContent().getFileName());
                    contentFields.setFilePath(wordData.getContent().getFilePath());
                    return contentFields;
                })
                .collect(Collectors.toList());

        return contentFieldsList;
    }


}
