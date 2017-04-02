/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.word

import android.os.Bundle
import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.activities.mvp.MonasticMVPActivity

class WordActivity : MonasticMVPActivity(R.drawable.article_top) {

    fun initPresenterView(): Pair<WordPresenter, WordView> = init(R.id.fragment_container)

    override fun initPresenter() {
        presenter = initPresenterView().first
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        (presenter as WordContract.Presenter).onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }
}
