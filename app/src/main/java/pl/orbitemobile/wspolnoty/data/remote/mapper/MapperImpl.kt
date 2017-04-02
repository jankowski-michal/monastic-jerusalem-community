package pl.orbitemobile.wspolnoty.data.remote.mapper

import org.jsoup.Connection

open class MapperImpl private constructor() : Mapper {
    override fun mapHours(response: Connection.Response) = HoursMapper.instance.mapHours(response)

    companion object {
        val instance = MapperImpl()
    }

    override fun mapArticle(response: Connection.Response, articleUrl: String) = SingleArticleMapper.instance.mapArticle(response, articleUrl)

    override fun mapReadingsForToday(response: Connection.Response) = TodayReadingsMapper.instance.mapReadingsForDay(response)

    override fun mapArticles(response: Connection.Response) = ArticlesMapper.instance.mapArticles(response)

    override fun mapNews(response: Connection.Response) = NewsMapper.instance.mapNews(response)
}
