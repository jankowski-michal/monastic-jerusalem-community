/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.home.domain

import io.reactivex.Single
import pl.orbitemobile.kotlinrx.fromIoToMainThread
import pl.orbitemobile.wspolnoty.data.RemoteRepository
import pl.orbitemobile.wspolnoty.data.entities.Article

class HomeScreen {

    private val remoteRepository: RemoteRepository = RemoteRepository()

    val remoteArticles: Single<Array<Article>>
        get() = remoteRepository.getArticles().fromIoToMainThread()
}
