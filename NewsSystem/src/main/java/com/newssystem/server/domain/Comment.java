package com.newssystem.server.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Sebek on 2016-10-01.
 */
@Document
public class Comment {

    @Id
    public String id;
    @NotNull(message = "Brak newsId")
    public String newsId;
    @NotNull
    @Size(min = 5, max = 2000 , message = "Minimum 5 znaków , maks 2000")
    public String comment;
    @NotNull
    @Size(min = 2, max = 20 ,  message = "Minimum 2 znaki , maks 20")
    public String author;
    @NotNull
    public String data;

    public Comment() {}

    public Comment(String newsId, String comment, String author, String data) {
        this.newsId = newsId;
        this.comment = comment;
        this.author = author;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
