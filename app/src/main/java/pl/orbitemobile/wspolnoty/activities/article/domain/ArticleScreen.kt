/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.article.domain

import io.reactivex.Single
import pl.orbitemobile.kotlinrx.fromIoToMainThread
import pl.orbitemobile.wspolnoty.data.RemoteRepository
import pl.orbitemobile.wspolnoty.data.entities.Article

class ArticleScreen {

    private val mRemoteRepository: RemoteRepository = RemoteRepository()

    fun getRemoteArticleDetails(articleDetailsUrl: String): Single<Article> =
            mRemoteRepository.getArticleDetails(articleDetailsUrl).fromIoToMainThread()
}