/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import pl.orbitemobile.wspolnoty.R

object DownloadViewUtil { //todo: refactor this so this will take layouts varargs as sources
    fun showErrorMessage(view: DownloadView, onRetryClick: () -> Unit) {
        view.progressBar?.visibility = View.GONE
        view.viewContent?.visibility = View.GONE
        view.errorLayout?.visibility = View.VISIBLE
        view.errorButton?.setOnClickListener { onRetryClick() }
    }

    fun showLoadingScreen(view: DownloadView) {
        view.viewContent?.visibility = View.GONE
        view.errorLayout?.visibility = View.GONE
        view.progressBar?.visibility = View.VISIBLE
    }

    fun showViewContent(view: DownloadView) {
        view.errorLayout?.visibility = View.GONE
        view.progressBar?.visibility = View.GONE
        view.viewContent?.visibility = View.VISIBLE
    }

    fun showNetworkToast(context: Context) = Toast.makeText(context, context.getString(R.string.no_network_message), Toast.LENGTH_LONG).show()
}
