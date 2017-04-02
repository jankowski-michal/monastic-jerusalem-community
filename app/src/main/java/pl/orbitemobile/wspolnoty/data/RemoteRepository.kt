/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.data

import pl.orbitemobile.kotlinrx.single
import pl.orbitemobile.wspolnoty.data.entities.Article
import pl.orbitemobile.wspolnoty.data.remote.Downloader
import pl.orbitemobile.wspolnoty.data.remote.Mapper

open class RemoteRepository {

    val HOMEPAGE = "http://wspolnoty-jerozolimskie.pl/"
    val NEWS = "http://wspolnoty-jerozolimskie.pl/category/aktualnosci/page/"
    var downloader: Downloader = Downloader()
    var mapper: Mapper = Mapper()

    fun getArticles() = single { downloadAndMapArticles() }

    fun getNews(page: Int) = single { downloadAndMapNews(page) }

    fun getArticleDetails(url: String) = single { downloadAndMapArticleDetails(url) }

    private fun downloadAndMapNews(page: Int): Array<Article> {
        val response = downloader.getResponse(NEWS + page)
        return mapper.mapNews(response)
    }

    private fun downloadAndMapArticleDetails(url: String): Article {
        val response = downloader.getResponse(url)
        return mapper.mapArticle(response, url)
    }

    protected fun downloadAndMapArticles(): Array<Article> {
        val response = downloader.getResponse(HOMEPAGE)
        return mapper.mapArticles(response)
    }
}
