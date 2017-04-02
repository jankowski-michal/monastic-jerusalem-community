/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.mvp

import android.view.View

object DownloadViewUtil {
    //fixme: viewContent should be list of views
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
}
