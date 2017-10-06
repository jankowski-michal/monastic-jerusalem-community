package pl.orbitemobile.wspolnoty.data.remote.mapper

import org.jsoup.Connection
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO
import pl.orbitemobile.wspolnoty.data.dto.ReadingDTO

open class MapperImpl private constructor() : Mapper {

    companion object {
        val instance = MapperImpl()
    }

    override fun mapArticle(response: Connection.Response, articleUrl: String): ArticleDTO = SingleArticleMapper.instance.mapArticle(response, articleUrl)

    override fun mapReadingsForToday(response: Connection.Response): List<ReadingDTO> = TodayReadingsMapper.instance.mapReadingsForDay(response)

    override fun mapArticles(response: Connection.Response): Array<ArticleDTO> = ArticlesMapper.instance.mapArticles(response)

    override fun mapNews(response: Connection.Response): Array<ArticleDTO> = NewsMapper.instance.mapNews(response)
}
