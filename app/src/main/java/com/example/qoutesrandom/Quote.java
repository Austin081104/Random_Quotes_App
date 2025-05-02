package com.example.qoutesrandom;

import com.google.gson.annotations.SerializedName;

public class Quote {
    @SerializedName("quote")
    private String quote;

    @SerializedName("author")
    private String author;

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }
}