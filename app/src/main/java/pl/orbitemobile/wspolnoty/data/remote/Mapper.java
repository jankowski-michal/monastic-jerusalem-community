/*
 * Copyright (C) 2017 Michał Jankowski.
 * www.michaeljankowski.com - michael.jankowski.com@gmail.com
 * All Rights Reserved.
 */
package pl.orbitemobile.wspolnoty.data.remote;

import org.jsoup.Connection;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.support.annotation.NonNull;

import java.io.IOException;

import pl.orbitemobile.wspolnoty.data.entities.Article;

public class Mapper {
    
    @NonNull
    public Article mapArticle(Connection.Response response) throws IOException {
        Article article = new Article();
        article.setTitle(getTitle(response));
        article.setImgUrl(getImgUrl(response));
        article.setDescription(getDescription(response));
        return article;
    }
    
    @NonNull
    public Article[] mapArticles(Connection.Response response) throws IOException {
        Elements articlesJsoup = getArticles(response);
        Article[] articles = new Article[articlesJsoup.size()];
        for (Element e : articlesJsoup) {
            articles[articlesJsoup.indexOf(e)] = getArticle(e);
        }
        return articles;
    }
    
    private String getDescription(Connection.Response response) throws IOException {
        return response.parse().getElementsByAttributeValue("class", "entry-content").html()
                .replaceAll("</p>", "\n")
                .replaceAll("<br>", "\n")
                .replaceAll("<[^>]*>", "")
                .trim()
                .replaceAll("\n ", "\n")
                .replaceAll(" \n", "\n")
                .replaceAll("\n\n\n\n\n\n", "\n")
                .replaceAll("\n\n\n\n\n", "\n")
                .replaceAll("\n\n\n\n", "\n")
                .replaceAll("\n\n\n", "\n")
                .replaceAll("&nbsp;", " ")
                .trim();
    }
    
    private String getImgUrl(Connection.Response response) throws IOException {
        return response.parse().getElementsByAttributeValue("class", "meta-image").get(0).getElementsByTag("img").get(0).absUrl("src");
    }
    
    private String getTitle(Connection.Response response) throws IOException {
        return response.parse().getElementsByAttributeValue("class", "entry-title").text();
    }
    
    private Elements getArticles(Connection.Response response) throws IOException {
        return response.parse().getElementsByAttributeValue("class", "vce-featured");
    }
    
    private Article getArticle(Element element) {
        String title = getTitle(element);
        String imgUrl = getImgUrl(element);
        String articlaUrl = getArticleUrl(element);
        return new Article(title, imgUrl, articlaUrl);
    }
    
    private String getTitle(Element article) {
        return article.getElementsByAttributeValue("class", "vce-featured-title").text();
    }
    
    private String getArticleUrl(Element article) {
        Elements elements = article.getElementsByAttributeValue("class", "vce-featured-link-article");
        return elements.get(0).absUrl("href");
    }
    
    private String getImgUrl(Element article) {
        Elements elements = article.getElementsByTag("img");
        return elements.get(0).absUrl("src");
    }
}
