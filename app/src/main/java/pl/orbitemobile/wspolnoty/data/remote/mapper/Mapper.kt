package pl.orbitemobile.wspolnoty.data.remote.mapper

import org.jsoup.Connection
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO
import pl.orbitemobile.wspolnoty.data.dto.ReadingDTO

interface Mapper {
    fun mapArticle(response: Connection.Response, articleUrl: String): ArticleDTO
    fun mapReadingTitleAndContent(response: Connection.Response): Pair<String, String> //todo: change method name, refactor and write tests
    fun mapReadingsForDay(response: Connection.Response): List<ReadingDTO> //todo: change method name, refactor and write tests
    fun mapArticles(response: Connection.Response): Array<ArticleDTO>
    fun mapNews(response: Connection.Response): Array<ArticleDTO>
}