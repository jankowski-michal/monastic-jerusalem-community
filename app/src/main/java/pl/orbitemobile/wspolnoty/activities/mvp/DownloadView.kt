/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.mvp

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import pl.orbitemobile.wspolnoty.R



interface DownloadView {
    var viewContent: View?
    var progressBar: View?
    var errorLayout: LinearLayout?
    var errorButton: TextView?
    fun showErrorMessage()
    fun showLoadingScreen()
    fun showNetworkToast()  = Toast.makeText(getContext(), getContext().getString(R.string.no_network_message), Toast.LENGTH_LONG).show()
    fun getContext(): Context
}