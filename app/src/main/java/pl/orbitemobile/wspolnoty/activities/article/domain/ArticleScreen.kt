/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.article.domain

import io.reactivex.Single
import pl.orbitemobile.kotlinrx.fromIoToMainThread
import pl.orbitemobile.wspolnoty.data.RemoteRepositoryImpl
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO

class ArticleScreen {

    private val mRemoteRepository: RemoteRepositoryImpl = RemoteRepositoryImpl.instance

    fun getRemoteArticleDetails(articleDetailsUrl: String): Single<ArticleDTO> =
            mRemoteRepository.getArticleDetails(articleDetailsUrl).fromIoToMainThread()
}