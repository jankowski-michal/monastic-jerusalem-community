/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.word

import android.os.Bundle
import io.reactivex.Single
import io.reactivex.SingleObserver
import pl.orbitemobile.mvp.MvpPresenter
import pl.orbitemobile.mvp.MvpView
import pl.orbitemobile.wspolnoty.activities.mvp.DownloadView
import pl.orbitemobile.wspolnoty.data.dto.ReadingDTO

class WordContract {
    abstract class View(layoutId: Int) : MvpView<Presenter>(layoutId), DownloadView {
        abstract fun showReadings(readings: List<ReadingDTO>)
    }

    interface Presenter : MvpPresenter<View>, SingleObserver<List<ReadingDTO>> {
        fun onRetryClick()
        fun onSaveInstanceState(outState: Bundle?)
    }

    interface UseCase {
        fun getTodayReading(): Single<List<ReadingDTO>>
    }
}