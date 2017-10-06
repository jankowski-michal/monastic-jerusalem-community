/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.where

import android.view.View
import pl.orbitemobile.mvp.bind
import pl.orbitemobile.wspolnoty.R

class WhereView : WhereContract.View(R.layout.where_view) {
    var mapLayout: View? = null

    override fun View.bindViews(): View {
        mapLayout = bind(R.id.map_layout)
        mapLayout?.setOnClickListener { presenter?.onMapClick() }
        return this
    }
}
