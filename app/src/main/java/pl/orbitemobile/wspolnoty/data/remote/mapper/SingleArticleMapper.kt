package pl.orbitemobile.wspolnoty.data.remote.mapper

import org.jsoup.Connection
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO
import pl.orbitemobile.wspolnoty.data.remote.parser.Parser


class SingleArticleMapper private constructor() {

    companion object {
        val instance = SingleArticleMapper()
    }

    fun mapArticle(response: Connection.Response, articleUrl: String): ArticleDTO {
        val title = getTitle(response)
        val imgUrl = getImgUrl(response)
        val description = getDescription(response)
        val article = ArticleDTO(title, imgUrl, articleUrl)
        article.description = description
        return article
    }

    private fun getDescription(response: Connection.Response) =
            Parser.instance.parse(response.parse()
                    .getElementsByAttributeValue("class", "entry-content")
                    .html())

    private fun getImgUrl(response: Connection.Response): String =
            response.parse().getElementsByAttributeValue("class", "meta-image")[0].getElementsByTag("img")[0].absUrl("src")

    private fun getTitle(response: Connection.Response): String =
            response.parse().getElementsByAttributeValue("class", "entry-title").text()
}