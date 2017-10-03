package pl.orbitemobile.wspolnoty.data.remote

import io.reactivex.Single
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO
import pl.orbitemobile.wspolnoty.data.dto.ReadingDTO
import pl.orbitemobile.wspolnoty.data.remote.download.DownloadManager
import pl.orbitemobile.wspolnoty.data.remote.mapper.Mapper

interface RemoteRepository {

    var downloader: DownloadManager

    var mapper: Mapper

    fun getArticles(): Single<Array<ArticleDTO>>

    fun getNews(page: Int): Single<Array<ArticleDTO>>

    fun getArticleDetails(url: String): Single<ArticleDTO>

    fun downloadTodayReading(): Single<List<ReadingDTO>>

}