/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.hours

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import pl.orbitemobile.mvp.bind
import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.activities.mvp.DownloadViewUtil
import pl.orbitemobile.wspolnoty.data.remote.parser.Parser

class HoursView : HoursContract.View(R.layout.hours_view) {
    override var viewContent: View? = null
    override var progressBar: View? = null
    override var errorLayout: LinearLayout? = null
    override var errorButton: TextView? = null
    override fun View.bindViews(): View {
        progressBar = bind(R.id.progress_bar)
        errorLayout = bind(R.id.error_layout)
        errorButton = bind(R.id.error_button)
        viewContent = bind(R.id.liturgyTimeTable)
        return this
    }

    override fun showHours(hours: String) {
        DownloadViewUtil.showViewContent(this)
        (viewContent as TextView).text = Parser.instance.fromHtml(hours).trim()
    }

    override fun showErrorMessage() = DownloadViewUtil.showErrorMessage(this) { presenter!!.onRetryClick() }

    override fun showLoadingScreen() = DownloadViewUtil.showLoadingScreen(this)

}