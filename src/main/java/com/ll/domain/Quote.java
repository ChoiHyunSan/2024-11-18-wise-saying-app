package com.ll.domain;

public class Quote {

    private Long id;
    private String wiseSaying;
    private String author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWiseSaying() {
        return wiseSaying;
    }

    public void setWiseSaying(String wiseSaying) {
        this.wiseSaying = wiseSaying;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
