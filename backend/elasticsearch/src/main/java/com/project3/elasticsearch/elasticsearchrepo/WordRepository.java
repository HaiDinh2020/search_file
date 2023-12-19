package com.project3.elasticsearch.elasticsearchrepo;

import com.project3.elasticsearch.entity.Word;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


import java.util.List;

public interface WordRepository extends ElasticsearchRepository<Word, String> {
    List<Word> findByContent(String content);

    @Query("{\"bool\": {\"must\": [{\"match_phrase\": {\"content\": \"?0\"}}]}}")
    List<Word> customFindByExacContent(String content);

    @Query("{\"bool\": {\"must\": [{\"match_all\": {\"\": \"?0\"}}]}}")
    List<String> findAll(String forder);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"content\": \"?0\"}}]}}")
    List<Word> customFindByContent(String content);

}

