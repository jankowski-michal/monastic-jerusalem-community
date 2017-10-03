/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.utils

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.widget.ImageView
import pl.orbitemobile.mvp.MVP
import pl.orbitemobile.wspolnoty.R

abstract class MonasticMVPActivity(val topDrawableId: Int, val setDisplayHomeAsUpEnabled: Boolean = true) : MVP.Activity(R.layout.base_activity) {

    protected lateinit var presenter: MVP.Presenter

    abstract fun initPresenter()
    /**
     * Example:
     * val presenterView: Pair<HomePresenter, HomeView> = init()
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbar()
        initPresenter()
        presenter.onStart(savedInstanceState)
    }

    private fun setToolbar() {
        val toolbar = findViewById(R.id.appbar_collapsing_toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(setDisplayHomeAsUpEnabled)
        val appbarCollapsingImage = findViewById(R.id.appbar_collapsing_image) as ImageView
        appbarCollapsingImage.setImageResource(topDrawableId)
    }

    override fun onDestroy() {
        presenter.onStop()
        super.onDestroy()
    }


    override fun onSupportNavigateUp(): Boolean {
        if (setDisplayHomeAsUpEnabled) {
            onBackPressed()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
}
