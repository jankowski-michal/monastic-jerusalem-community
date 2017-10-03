/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.news

import android.view.MenuItem
import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.activities.utils.MonasticMVPActivity

class NewsActivity : MonasticMVPActivity(R.drawable.news_top) {

    fun initPresenterView(): Pair<NewsPresenter, NewsView> = init(R.id.fragment_container)

    override fun initPresenter() {
        presenter = initPresenterView().first
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            presenter.onOptionsItemSelected(item) || super.onOptionsItemSelected(item)

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
