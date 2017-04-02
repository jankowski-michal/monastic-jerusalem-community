/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.home

import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.activities.mvp.MonasticMVPActivity

class HomeActivity : MonasticMVPActivity(R.drawable.home_top, false) {

    private fun initPresenterView(): Pair<HomePresenter, HomeView> = init(R.id.fragment_container)

    override fun initPresenter() {
        presenter = initPresenterView().first
    }
}
