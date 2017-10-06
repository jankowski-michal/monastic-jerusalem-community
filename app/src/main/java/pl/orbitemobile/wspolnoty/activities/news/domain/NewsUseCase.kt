/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.news.domain

import io.reactivex.Single
import pl.orbitemobile.kotlinrx.fromIoToMainThread
import pl.orbitemobile.wspolnoty.activities.news.NewsContract
import pl.orbitemobile.wspolnoty.data.RemoteRepositoryImpl
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO

class NewsUseCase : NewsContract.UseCase {

    private val mRemoteRepository: RemoteRepositoryImpl = RemoteRepositoryImpl.instance

    override fun getRemoteArticles(page: Int): Single<Array<ArticleDTO>> =
            mRemoteRepository.getNews(page).fromIoToMainThread()

}
