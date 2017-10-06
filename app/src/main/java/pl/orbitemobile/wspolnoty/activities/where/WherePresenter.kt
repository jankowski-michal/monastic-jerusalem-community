/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.where

import io.reactivex.disposables.CompositeDisposable
import pl.orbitemobile.wspolnoty.activities.where.maps.MapsIntentBuilder

class WherePresenter(override var disposable: CompositeDisposable? = null,
                     override var view: WhereContract.View? = null) : WhereContract.Presenter {
    override fun onViewAttached() {}

    override fun onMapClick() {
        val mapsPresenter = MapsIntentBuilder()
        val mapsIntent = mapsPresenter.mapsIntent(view?.context)
        view?.context?.startActivity(mapsIntent)
    }
}
