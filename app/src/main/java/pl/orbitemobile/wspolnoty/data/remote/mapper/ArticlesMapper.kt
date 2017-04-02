package pl.orbitemobile.wspolnoty.data.remote.mapper

import android.util.Log
import org.jsoup.Connection
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO

class ArticlesMapper private constructor() {
    companion object {
        val instance = ArticlesMapper()
    }

    fun mapArticles(response: Connection.Response): Array<ArticleDTO> {
        val articles = getArticles(response)
        return articles.map { getArticle(it) }.toTypedArray()
    }

    private fun getArticles(response: Connection.Response): Elements =
            response.parse().getElementsByAttributeValue("class", "vce-featured")

    private fun getArticle(element: Element): ArticleDTO {
        val title = getTitle(element)
        val imgUrl = getImgUrl(element)
        val articleUrl = getArticleUrl(element)
        return ArticleDTO(title, imgUrl, articleUrl)
    }

    private fun getTitle(article: Element): String =
            article.getElementsByAttributeValue("class", "vce-featured-title").text()


    private fun getArticleUrl(article: Element): String {
        val elements = article.getElementsByAttributeValue("class", "vce-featured-link-article")
        return elements[0].absUrl("href")
    }

    private fun getImgUrl(article: Element): String {
        val elements = article.getElementsByTag("img")
        return elements[0].absUrl("src")
    }

}