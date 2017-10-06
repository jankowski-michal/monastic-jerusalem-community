/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.where


import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.activities.mvp.MonasticMVPActivity

class WhereActivity : MonasticMVPActivity(R.drawable.where_top) {

    private fun initPresenterView(): Pair<WherePresenter, WhereView> = init(R.id.fragment_container)

    override fun initPresenter() {
        presenter = initPresenterView().first
    }
}
