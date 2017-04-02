/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.data.remote

import android.util.Log
import org.jsoup.Connection
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import pl.orbitemobile.wspolnoty.data.entities.Article
import pl.orbitemobile.wspolnoty.data.remote.HtmlParser.HtmlParser

open class Mapper {

    val parser = HtmlParser()

    open fun mapArticle(response: Connection.Response, articleUrl: String): Article {
        val title = getTitle(response)
        val imgUrl = getImgUrl(response)
        val description = getDescription(response)
        val article = Article(title, imgUrl, articleUrl)
        article.description = description
        return article
    }

    open fun mapArticles(response: Connection.Response): Array<Article> {
        val articles = getArticles(response)
        Log.d("Mapper", "Article size: " + articles.size)
        return articles.map { getArticle(it) }.toTypedArray()
    }

    open fun mapNews(response: Connection.Response): Array<Article> {
        val news = getNews(response)
        Log.d("Mapper", news.html())
        Log.d("Mapper", "news size: " + news.size)
        return news.map { getSingleNews(it) }.toTypedArray()
    }

    private fun getDescription(response: Connection.Response) =
            parser.parse(response.parse()
                    .getElementsByAttributeValue("class", "entry-content")
                    .html())

    private fun getImgUrl(response: Connection.Response): String {
        return response.parse().getElementsByAttributeValue("class", "meta-image")[0].getElementsByTag("img")[0].absUrl("src")
    }

    private fun getTitle(response: Connection.Response): String {
        return response.parse().getElementsByAttributeValue("class", "entry-title").text()
    }

    private fun getArticles(response: Connection.Response): Elements {
        return response.parse().getElementsByAttributeValue("class", "vce-featured")
    }

    private fun getNews(response: Connection.Response): Elements {
        return response.parse().getElementsByTag("article")
    }

    private fun getArticle(element: Element): Article {
        val title = getTitle(element)
        val imgUrl = getImgUrl(element)
        val articleUrl = getArticleUrl(element)
        return Article(title, imgUrl, articleUrl)
    }

    private fun getSingleNews(element: Element): Article {
        val title = getNewsTitle(element)
        val imgUrl = getNewsImgUrl(element)
        val articleUrl = getNewsUrl(element)
        val article = Article(title, imgUrl, articleUrl)
        article.dataCreated = getNewsDataCreated(element)
        return article
    }

    private fun getTitle(article: Element): String {
        return article.getElementsByAttributeValue("class", "vce-featured-title").text()
    }

    private fun getNewsTitle(article: Element): String {
        return article.getElementsByAttributeValue("class", "entry-title").text()
    }

    private fun getNewsImgUrl(article: Element): String {
        return article.getElementsByAttributeValue("class", "meta-image")[0].getElementsByTag("img")[0].absUrl("src")
    }

    private fun getNewsDataCreated(article: Element): String {
        return article.getElementsByAttributeValue("class", "updated").text()
    }

    private fun getNewsUrl(article: Element): String {
        val elements = article.getElementsByAttributeValue("class", "entry-title")[0].getElementsByTag("a")
        return elements[0].absUrl("href")
    }

    private fun getArticleUrl(article: Element): String {
        val elements = article.getElementsByAttributeValue("class", "vce-featured-link-article")
        return elements[0].absUrl("href")
    }

    private fun getImgUrl(article: Element): String {
        val elements = article.getElementsByTag("img")
        return elements[0].absUrl("src")
    }
}
