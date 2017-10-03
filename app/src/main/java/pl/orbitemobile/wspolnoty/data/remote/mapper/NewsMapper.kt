package pl.orbitemobile.wspolnoty.data.remote.mapper

import org.jsoup.Connection
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO

class NewsMapper private constructor() {
    companion object {
        val instance = NewsMapper()
    }

    fun mapNews(response: Connection.Response): Array<ArticleDTO> {
        val news = getNews(response)
        return news.map { getSingleNews(it) }.toTypedArray()
    }

    private fun getNews(response: Connection.Response): Elements =
            response.parse().getElementsByTag("article")

    private fun getSingleNews(element: Element): ArticleDTO {
        val title = getNewsTitle(element)
        val imgUrl = getNewsImgUrl(element)
        val articleUrl = getNewsUrl(element)
        val article = ArticleDTO(title, imgUrl, articleUrl)
        article.dataCreated = getNewsDataCreated(element)
        return article
    }


    private fun getNewsTitle(article: Element): String =
            article.getElementsByAttributeValue("class", "entry-title").text()

    private fun getNewsImgUrl(article: Element): String =
            article.getElementsByAttributeValue("class", "meta-image")[0].getElementsByTag("img")[0].absUrl("src")

    private fun getNewsDataCreated(article: Element): String =
            article.getElementsByAttributeValue("class", "updated").text()

    private fun getNewsUrl(article: Element): String {
        val elements = article.getElementsByAttributeValue("class", "entry-title")[0].getElementsByTag("a")
        return elements[0].absUrl("href")
    }
}
