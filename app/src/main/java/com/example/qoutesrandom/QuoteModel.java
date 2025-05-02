package com.example.qoutesrandom;

public class QuoteModel {
    private final String id;
    private final String quote;
    private final String author;

    public QuoteModel(String id, String quote, String author) {
        this.id = id;
        this.quote = quote;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }
}