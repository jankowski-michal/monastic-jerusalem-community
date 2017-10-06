/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.contact

import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.activities.mvp.MonasticMVPActivity

class ContactActivity : MonasticMVPActivity(R.drawable.contact_top) {

    private fun initPresenterView(): Pair<ContactPresenter, ContactView> = init(R.id.fragment_container)

    override fun initPresenter() {
        presenter = initPresenterView().first
    }
}
