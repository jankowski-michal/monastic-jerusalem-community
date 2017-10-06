/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.hours

import pl.orbitemobile.mvp.MvpPresenter
import pl.orbitemobile.mvp.MvpView

class HoursContract {
    abstract class View(layoutId: Int) : MvpView<Presenter>(layoutId)

    interface Presenter : MvpPresenter<View>
}
