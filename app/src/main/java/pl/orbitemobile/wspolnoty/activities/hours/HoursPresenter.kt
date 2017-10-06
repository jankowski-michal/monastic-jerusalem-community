/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.hours

import io.reactivex.disposables.CompositeDisposable

class HoursPresenter(override var disposable: CompositeDisposable? = null,
                     override var view: HoursContract.View? = null) : HoursContract.Presenter {

    override fun onViewAttached() {
    }
}
