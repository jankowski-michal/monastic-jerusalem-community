/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.mvp

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import pl.orbitemobile.mvp.MvpActivity
import pl.orbitemobile.mvp.MvpPresenter
import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.utilities.AboutDialogBuilder

abstract class MonasticMVPActivity(private val topDrawableId: Int, private val setDisplayHomeAsUpEnabled: Boolean = true) : MvpActivity(R.layout.base_activity) {

    protected lateinit var presenter: MvpPresenter<*>

    abstract fun initPresenter()
    /**
     * Example:
     * fun initPresenter() {
     *      val presenterView: Pair<HomePresenter, HomeView> = init()
     * }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.about_app) {
            showAboutDialog()
            return true
        }
        return false
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

    fun showAboutDialog() = AboutDialogBuilder.instance.showAboutDialog(this)
}
