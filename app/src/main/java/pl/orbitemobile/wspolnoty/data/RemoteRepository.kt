/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.data

import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.orbitemobile.wspolnoty.data.entities.Article
import pl.orbitemobile.wspolnoty.data.remote.Downloader
import pl.orbitemobile.wspolnoty.data.remote.Mapper

open class RemoteRepository {

    val HOMEPAGE = "http://wspolnoty-jerozolimskie.pl/"

    val NEWS = "http://wspolnoty-jerozolimskie.pl/category/aktualnosci/page/"

    val mDownloader: Downloader = Downloader()

    val mMapper: Mapper = Mapper()

    fun getArticles(): Single<Array<Article>> {
        return Single.create(SingleOnSubscribe<Array<Article>> { e ->
            var articles = downloadAndMapArticles()
            e.onSuccess(articles)
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getNews(page: Int): Single<Array<Article>> {
        return Single.create(SingleOnSubscribe<Array<Article>> { e ->
            val articles = downloadAndMapNews(page)
            e.onSuccess(articles)
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getArticleDetails(eventUrl: String): Single<Article> {
        return Single.create(SingleOnSubscribe<Article> { e ->
            val article = downloadAndMapArticleDetails(eventUrl)
            e.onSuccess(article)
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    protected fun downloadAndMapNews(page: Int): Array<Article>? {
        val response = mDownloader.getResponse(NEWS + page) ?: return null
        return mMapper.mapNews(response)
    }

    protected fun downloadAndMapArticleDetails(eventUrl: String): Article? {
        val response = mDownloader.getResponse(eventUrl)
        response ?: return null
        return mMapper.mapArticle(response, eventUrl)
    }

    /*todo: test methods when downloader.getResponse returns null*/
    protected fun downloadAndMapArticles(): Array<Article>? {
        val response = mDownloader.getResponse(HOMEPAGE)
        response ?: return null
        return mMapper.mapArticles(response)
    }
}
