/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.hours

import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.activities.mvp.MonasticMVPActivity

class HoursActivity : MonasticMVPActivity(R.drawable.hours_top) {

    private fun initPresenterView(): Pair<HoursPresenter, HoursView> = init(R.id.fragment_container)

    override fun initPresenter() {
        presenter = initPresenterView().first
    }
}
