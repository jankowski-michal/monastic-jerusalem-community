/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.news.domain

import io.reactivex.Single
import pl.orbitemobile.kotlinrx.fromIoToMainThread
import pl.orbitemobile.wspolnoty.data.RemoteRepository
import pl.orbitemobile.wspolnoty.data.entities.Article

class NewsScreen {

    private val mRemoteRepository: RemoteRepository = RemoteRepository()

    fun getRemoteArticles(page: Int): Single<Array<Article>> =
            mRemoteRepository.getNews(page).fromIoToMainThread()

}
