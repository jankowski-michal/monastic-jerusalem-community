/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.news

import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.activities.mvp.MonasticMVPActivity

class NewsActivity : MonasticMVPActivity(R.drawable.news_top) {

    private fun initPresenterView(): Pair<NewsPresenter, NewsView> = init(R.id.fragment_container)

    override fun initPresenter() {
        presenter = initPresenterView().first
    }

}
