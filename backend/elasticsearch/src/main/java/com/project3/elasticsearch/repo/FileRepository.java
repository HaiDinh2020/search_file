package com.project3.elasticsearch.repo;

import com.project3.elasticsearch.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface FileRepository {
    public void init();

    public void save(MultipartFile file);

    public void deleteAll();

}
