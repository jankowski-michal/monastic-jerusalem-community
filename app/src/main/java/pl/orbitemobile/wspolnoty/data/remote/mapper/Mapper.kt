package pl.orbitemobile.wspolnoty.data.remote.mapper

import org.jsoup.Connection
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO
import pl.orbitemobile.wspolnoty.data.dto.ReadingDTO

interface Mapper {
    fun mapArticle(response: Connection.Response, articleUrl: String): ArticleDTO
    fun mapReadingsForToday(response: Connection.Response): List<ReadingDTO>
    fun mapArticles(response: Connection.Response): Array<ArticleDTO>
    fun mapNews(response: Connection.Response): Array<ArticleDTO>
    fun mapHours(response: Connection.Response): String
}