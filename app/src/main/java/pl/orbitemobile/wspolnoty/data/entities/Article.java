/*
 * Copyright (C) 2017 Michał Jankowski.
 * www.michaeljankowski.com - michael.jankowski.com@gmail.com
 * All Rights Reserved.
 */
package pl.orbitemobile.wspolnoty.data.entities;

public class Article {
    
    private String title;
    
    private String imgUrl;
    
    private String articleUrl;
    
    private String description;
    
    public Article() {
    }
    
    public Article(String title, String imgUrl, String articleUrl) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.articleUrl = articleUrl;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setImgUrl(final String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
    public String getImgUrl() {
        return imgUrl;
    }
    
    public String getArticleUrl() {
        return articleUrl;
    }
}
