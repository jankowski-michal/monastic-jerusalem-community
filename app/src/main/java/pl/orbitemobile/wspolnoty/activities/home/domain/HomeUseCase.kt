/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.home.domain

import io.reactivex.Single
import pl.orbitemobile.kotlinrx.fromIoToMainThread
import pl.orbitemobile.wspolnoty.activities.home.HomeContract
import pl.orbitemobile.wspolnoty.data.RemoteRepositoryImpl
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO

class HomeUseCase: HomeContract.UseCase {

    private val remoteRepository: RemoteRepositoryImpl = RemoteRepositoryImpl.instance

    override val remoteArticles: Single<Array<ArticleDTO>>
        get() = remoteRepository.getArticles().fromIoToMainThread()
}
