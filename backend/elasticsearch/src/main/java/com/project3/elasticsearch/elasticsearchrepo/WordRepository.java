package com.project3.elasticsearch.elasticsearchrepo;

import com.project3.elasticsearch.entity.Word;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public interface WordRepository extends ElasticsearchRepository<Word, String> {
    List<Word> findByContent(String content);
    Iterable<Word> findByFileNameEndingWith(String suffix);
    @Query("{\"bool\": {\"must\": [{\"match_phrase\": {\"content\": \"?0\"}}]}}")
    List<SearchHit<Word>> customFindByExacContent(String content);

    @Query("{\"bool\": {\"must\": [{\"match_all\": {\"\": \"?0\"}}]}}")
    List<String> findAll(String forder);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"content\": \"?0\"}}]}}")
    List<Word> customFindByContent(String content);

    @Query("{\"bool\": {\"should\": [{\"match\": {\"content\": \"?0\"}}, {\"match\": {\"content\": \"?1\"}}]}}")
    List<SearchHit<Word>>  findByContentWithScore(String contenta, String contentb);

    Long deleteByFileName(String fileName);


}

