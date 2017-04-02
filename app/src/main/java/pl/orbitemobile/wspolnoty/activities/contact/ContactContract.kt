/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.contact

import pl.orbitemobile.mvp.MvpPresenter
import pl.orbitemobile.mvp.MvpView

class ContactContract {

    abstract class View(layoutId: Int) : MvpView<Presenter>(layoutId)

    interface Presenter : MvpPresenter<View> {

        fun onPhoneClick()

        fun onMailClick()

        fun onWebsiteClick()
    }
}
