/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.word

import android.os.Bundle
import io.reactivex.Single
import io.reactivex.SingleObserver
import pl.orbitemobile.mvp.MVP
import pl.orbitemobile.wspolnoty.activities.utils.DownloadView
import pl.orbitemobile.wspolnoty.data.dto.ReadingDTO

class WordContract {
    abstract class View(layoutId: Int) : MVP.BaseView<Presenter>(layoutId), DownloadView {
        abstract fun showReadings(readings: List<ReadingDTO>)
    }

    abstract class Presenter : MVP.BasePresenter<View>(), SingleObserver<List<ReadingDTO>> {
        abstract fun onRetryClick()
        abstract fun onSaveInstanceState(outState: Bundle?)
    }

    interface UseCase {
        fun getTodayReading(): Single<List<ReadingDTO>>
    }
}