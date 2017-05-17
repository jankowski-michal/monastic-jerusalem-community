/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.data.remote;

import android.support.annotation.NonNull;

import org.jsoup.Connection;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

    @NonNull
    public Article[] mapNews(Connection.Response response) throws IOException {
        Elements newsJsoup = getNews(response);
        Article[] articles = new Article[newsJsoup.size()];
        for (Element e : newsJsoup) {
            articles[newsJsoup.indexOf(e)] = getSingleNews(e);
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

    private Elements getNews(Connection.Response response) throws IOException {
        return response.parse().getElementsByTag("article");
    }

    private Article getArticle(Element element) {
        String title = getTitle(element);
        String imgUrl = getImgUrl(element);
        String articleUrl = getArticleUrl(element);
        return new Article(title, imgUrl, articleUrl);
    }

    private Article getSingleNews(Element element) {
        String title = getNewsTitle(element);
        String imgUrl = getNewsImgUrl(element);
        String articleUrl = getNewsUrl(element);
        Article article = new Article(title, imgUrl, articleUrl);
        article.setDataCreated(getNewsDataCreated(element));
        return article;
    }

    private String getTitle(Element article) {
        return article.getElementsByAttributeValue("class", "vce-featured-title").text();
    }

    private String getNewsTitle(Element article) {
        return article.getElementsByAttributeValue("class", "entry-title").text();
    }

    private String getNewsImgUrl(Element article) {
        return article.getElementsByAttributeValue("class", "meta-image").get(0).getElementsByTag("img").get(0).absUrl("src");
    }

    private String getNewsDataCreated(Element article) {
        return article.getElementsByAttributeValue("class", "updated").text();
    }

    private String getNewsUrl(Element article) {
        Elements elements = article.getElementsByAttributeValue("class", "entry-title").get(0).getElementsByTag("a");
        return elements.get(0).absUrl("href");
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
