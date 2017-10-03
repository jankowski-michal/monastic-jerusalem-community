package pl.orbitemobile.wspolnoty.activities.word

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import pl.orbitemobile.mvp.bind
import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.activities.utils.DownloadViewUtil
import pl.orbitemobile.wspolnoty.activities.word.view.ReadingAdapter
import pl.orbitemobile.wspolnoty.data.dto.ReadingDTO


class WordView : WordContract.View(R.layout.word_view) {

    override var viewContent: View? = null
    override var progressBar: View? = null
    override var errorLayout: LinearLayout? = null
    override var errorButton: TextView? = null
    var loadingErrorLayout: View? = null
    var recyclerView: RecyclerView? = null

    override fun View.bindViews(): View {
        loadingErrorLayout = bind(R.id.loading_error_layout)
        recyclerView = bind(R.id.word_recycler_view)
        viewContent = recyclerView
        progressBar = bind(R.id.progress_bar)
        errorLayout = bind(R.id.error_layout)
        errorButton = bind(R.id.error_button)
        return this
    }

    override fun showNetworkToast() = DownloadViewUtil.showNetworkToast(context)

    override fun showErrorMessage() {
        loadingErrorLayout?.visibility = View.VISIBLE
        DownloadViewUtil.showErrorMessage(this) { mPresenter?.onRetryClick() }
    }

    override fun showLoadingScreen() {
        loadingErrorLayout?.visibility = View.VISIBLE
        DownloadViewUtil.showLoadingScreen(this)
    }


    override fun showReadings(readings: List<ReadingDTO>) {
        loadingErrorLayout?.visibility = View.GONE
        recyclerView?.visibility = View.VISIBLE
        DownloadViewUtil.showViewContent(this)
        readings.display()
    }

    private fun List<ReadingDTO>.display() {
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = ReadingAdapter(this)
    }

}