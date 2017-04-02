/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.hours

import io.reactivex.Single
import io.reactivex.SingleObserver
import pl.orbitemobile.mvp.MvpPresenter
import pl.orbitemobile.mvp.MvpView
import pl.orbitemobile.wspolnoty.activities.mvp.DownloadView

class HoursContract {
    abstract class View(layoutId: Int) : MvpView<Presenter>(layoutId), DownloadView {
        abstract fun showHours(string: String)
    }

    interface Presenter : MvpPresenter<View>, SingleObserver<String> {
        fun onRetryClick()
    }

    interface UseCase {
        fun getRemoteHours(): Single<String>
    }
}
