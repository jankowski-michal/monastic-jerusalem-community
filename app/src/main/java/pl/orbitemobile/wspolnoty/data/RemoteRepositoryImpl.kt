/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.data

import pl.orbitemobile.kotlinrx.single
import pl.orbitemobile.wspolnoty.data.remote.RemoteRepository
import pl.orbitemobile.wspolnoty.data.remote.download.DownloadManager
import pl.orbitemobile.wspolnoty.data.remote.download.DownloadManagerImpl
import pl.orbitemobile.wspolnoty.data.remote.download.Urls
import pl.orbitemobile.wspolnoty.data.remote.mapper.Mapper
import pl.orbitemobile.wspolnoty.data.remote.mapper.MapperImpl

class RemoteRepositoryImpl private constructor() : RemoteRepository {

    override var downloader: DownloadManager = DownloadManagerImpl.instance

    override var mapper: Mapper = MapperImpl.instance

    override fun getArticles() = single { downloadAndMapArticles() }

    override fun getHours() = single { mapper.mapHours(downloader.getResponse(Urls.HOMEPAGE)) }

    override fun getNews(page: Int) = single { page.downloadAndMapNews() }

    override fun getArticleDetails(url: String) = single { url.downloadAndMapArticleDetails() }

    override fun downloadTodayReading() = single { mapper.mapReadingsForToday(downloader.getResponse(Urls.READINGS_FOR_TODAY)) }

    private fun Int.downloadAndMapNews() = mapper.mapNews(downloader.getResponse(Urls.NEWS + this))

    private fun String.downloadAndMapArticleDetails() = mapper.mapArticle(downloader.getResponse(this), this)

    private fun downloadAndMapArticles() = mapper.mapArticles(downloader.getResponse(Urls.HOMEPAGE))

    companion object {
        val instance = RemoteRepositoryImpl()
    }
}
