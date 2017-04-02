/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.word.domain

import io.reactivex.Single
import pl.orbitemobile.kotlinrx.fromIoToMainThread
import pl.orbitemobile.kotlinrx.single
import pl.orbitemobile.wspolnoty.activities.word.WordContract
import pl.orbitemobile.wspolnoty.data.RemoteRepositoryImpl
import pl.orbitemobile.wspolnoty.data.dto.ReadingDTO

class WordUseCase : WordContract.UseCase {
    private val remoteRepository = RemoteRepositoryImpl.instance

    override fun getTodayReading(): Single<List<ReadingDTO>> =remoteRepository.downloadTodayReading().fromIoToMainThread()

}
