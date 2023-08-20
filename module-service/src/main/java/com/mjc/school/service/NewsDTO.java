package com.mjc.school.service;

import java.time.LocalDateTime;

public class NewsDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;

    public NewsDTO(Long id, String title, String content, LocalDateTime createDate, LocalDateTime lastUpdateDate, Long authorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public Long getAuthorId() {
        return authorId;
    }
}
