/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.article

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.widget.ImageView
import pl.orbitemobile.mvp.MVP
import pl.orbitemobile.wspolnoty.R

class ArticleActivity : MVP.Activity(R.id.fragment_container) {

    lateinit var appbarCollapsingImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)
        val toolbar = findViewById(R.id.appbar_collapsing_toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        bindViews()
        appbarCollapsingImage.setImageResource(R.drawable.article_top)
        init<ArticleContract.Presenter,
                ArticleContract.View,
                ArticlePresenter,
                ArticleView>()
    }

    fun bindViews() {
        appbarCollapsingImage = findViewById(R.id.appbar_collapsing_image) as ImageView
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
