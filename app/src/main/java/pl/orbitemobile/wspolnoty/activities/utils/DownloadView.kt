/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.utils

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView


interface DownloadView {
    var viewContent: View?
    var progressBar: View?
    var errorLayout: LinearLayout?
    var errorButton: TextView?
    fun showErrorMessage()
    fun showLoadingScreen()
    fun showNetworkToast()
}