package com.project3.elasticsearch.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

//@AllArgsConstructor
//@NoArgsConstructor
@Data
@Builder
@Document(indexName = "words")
public class Word {

    @Id
    private String id;

    private String content;
    @Field(type = FieldType.Integer)
    private int fileId;

    public Word() {
    }

    public Word(String id, String content, int fileId) {
        this.id = id;
        this.content = content;
        this.fileId = fileId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }
}
