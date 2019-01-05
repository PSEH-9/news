package com.example.news.demonews.model;

import java.util.List;


public class News {
    private String country;
    private String category;
    private String keyword;
    private String title;
    private String description;
    private String newsURL;

    public News(String country, String category, String keyword,
                   String title, String description, String newsURL) {
        super();
        this.country = country;
        this.category = category;
        this.keyword = keyword;
        this.title = title;
        this.description = description;
        this.newsURL = newsURL;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNewsURL() {
        return newsURL;
    }

    public void setNewsURL(String newsURL) {
        this.newsURL = newsURL;
    }


    @Override
    public String toString() {
        return String.format(
                "News [country=%s, category=%s, title=%s, keyword=%s, description=%s, newsurl=%s]", country,
                category, title, keyword, description, newsURL);
    }
}