/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.article

import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import pl.orbitemobile.mvp.MVP
import pl.orbitemobile.wspolnoty.BaseApplication
import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.utilities.AnalyticsLogger

class ArticleView : ArticleContract.View, MVP.BaseView<ArticleContract.Presenter>(R.layout.article_view) {
    lateinit var articleTitle: TextView
    lateinit var articleDescription: TextView
    lateinit var progressBar: View
    lateinit var errorLayout: LinearLayout
    lateinit var errorButton: TextView
    lateinit var articleLayout: LinearLayout
    lateinit var appbarCollapsingImage: ImageView
    lateinit var showArticleButton: TextView

    val TAG = ArticleView::class.java.simpleName!!

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) =
            super.onCreateView(inflater, container, savedInstanceState).bindViews()

    /*todo: add to mvp*/
    fun View.findTextView(id: Int) = findViewById(id) as TextView

    fun View.findLinearLayout(id: Int) = findViewById(id) as LinearLayout

    fun View.bindViews(): View {
        articleTitle = findTextView(R.id.article_title)
        articleDescription = findTextView(R.id.article_description)
        progressBar = findViewById(R.id.progress_bar)
        errorLayout = findLinearLayout(R.id.error_layout)
        errorButton = findTextView(R.id.error_button)
        articleLayout = findLinearLayout(R.id.article_layout)
        showArticleButton = findTextView(R.id.show_article_button)
        appbarCollapsingImage = activity.findViewById(R.id.appbar_collapsing_image) as ImageView
        return this
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun showErrorMessage() {
        progressBar.visibility = View.GONE
        articleLayout.visibility = View.GONE
        errorLayout.visibility = View.VISIBLE
        errorButton.setOnClickListener { mPresenter!!.onRetryClick() }
    }

    override fun showLoadingScreen() {
        articleLayout.visibility = View.GONE
        errorLayout.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    override fun showNetworkToast() {
        Toast.makeText(context, context.getString(R.string.no_network_message), Toast.LENGTH_LONG).show()
    }

    override fun showArticleDetails() {
        errorLayout.visibility = View.GONE
        progressBar.visibility = View.GONE
        articleLayout.visibility = View.VISIBLE
        showArticleButton.setOnClickListener { mPresenter!!.onShowWebsiteClick() }
    }

    fun setPresenter(presenter: ArticleContract.Presenter) {
        mPresenter = presenter
    }

    override fun setDescription(description: String) {
        articleDescription.movementMethod = LinkMovementMethod.getInstance()
        articleDescription.text = SpannableString(Html.fromHtml(description))
    }

    override fun setTitle(title: String) {
        articleTitle.text = title
    }

    override fun setImgUrl(imgUrl: String) {
        Picasso.with(context)
                .load(imgUrl)
                .into(appbarCollapsingImage)
    }

    override fun getIntent() = activity.intent

    private fun logAnalytics() {
        val logger = AnalyticsLogger()
        val baseApplication = activity.application as BaseApplication
        logger.LogAnalytics(TAG, baseApplication)
    }


}
