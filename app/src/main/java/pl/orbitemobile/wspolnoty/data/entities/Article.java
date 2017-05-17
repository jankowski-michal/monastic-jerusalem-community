/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.data.entities;

public class Article {
    
    private String title;
    
    private String imgUrl;
    
    private String articleUrl;
    
    private String description;
    
    private String dataCreated;
    
    public Article() {
    }
    
    public Article(String title, String imgUrl, String articleUrl) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.articleUrl = articleUrl;
    }
    
    public String getDataCreated() {
        return dataCreated;
    }
    
    public void setDataCreated(final String dataCreated) {
        this.dataCreated = dataCreated;
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
    
    public String getImgUrl() {
        return imgUrl;
    }
    
    public void setImgUrl(final String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
    public String getArticleUrl() {
        return articleUrl;
    }
}
