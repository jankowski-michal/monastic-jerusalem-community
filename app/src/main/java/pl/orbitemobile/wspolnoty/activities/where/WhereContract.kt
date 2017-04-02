/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.where

import pl.orbitemobile.mvp.MvpPresenter
import pl.orbitemobile.mvp.MvpView

class WhereContract {

    abstract class View(layoutId: Int) : MvpView<Presenter>(layoutId)

    interface Presenter : MvpPresenter<View> {
        fun onMapClick()
    }
}
