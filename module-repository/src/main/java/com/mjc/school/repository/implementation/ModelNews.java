package com.mjc.school.repository.implementation;

import java.time.LocalDateTime;
import java.util.Objects;

public class ModelNews {
    private int id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private int authorId;

    public ModelNews(int id, String title, String content, LocalDateTime createDate, LocalDateTime lastUpdateDate, int authorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.authorId = authorId;
    }

    public ModelNews() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelNews modelNews = (ModelNews) o;
        return Objects.equals(id, modelNews.id) && Objects.equals(title, modelNews.title) && Objects.equals(content, modelNews.content) && Objects.equals(createDate, modelNews.createDate) && Objects.equals(lastUpdateDate, modelNews.lastUpdateDate) && Objects.equals(authorId, modelNews.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createDate, lastUpdateDate, authorId);
    }
}
