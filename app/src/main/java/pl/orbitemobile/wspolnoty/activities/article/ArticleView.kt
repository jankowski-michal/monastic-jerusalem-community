/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import pl.orbitemobile.wspolnoty.BaseApplication
import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.mvp.MVP
import pl.orbitemobile.wspolnoty.utilities.AnalyticsLogger

class ArticleView : ArticleContract.View, MVP.BaseView<ArticleContract.Presenter>(R.layout.article_view) {
    lateinit var articleTitle: TextView
    lateinit var articleDescription: TextView
    lateinit var mProgressBar: View
    lateinit var mErrorLayout: LinearLayout
    lateinit var mErrorButton: TextView
    lateinit var mArticleLayout: LinearLayout
    lateinit var appbarCollapsingImage: ImageView

    val TAG = ArticleView::class.java.simpleName!!

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = super.onCreateView(inflater, container, savedInstanceState)
        bindViews(root)
        return root
    }

    fun View.findTextView(id: Int) = findViewById(id) as TextView
    fun View.findLinearLayout(id: Int) = findViewById(id) as LinearLayout

    fun bindViews(root: View) {
        articleTitle = root.findTextView(R.id.article_title)
        articleDescription = root.findTextView(R.id.article_description)
        mProgressBar = root.findViewById(R.id.progress_bar)
        mErrorLayout = root.findLinearLayout(R.id.error_layout)
        mErrorButton = root.findTextView(R.id.error_button)
        mArticleLayout = root.findLinearLayout(R.id.article_layout)
        appbarCollapsingImage = activity.findViewById(R.id.appbar_collapsing_image) as ImageView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun showErrorMessage() {
        setGone(mProgressBar, mArticleLayout)
        mErrorLayout.setVisible()
        mErrorButton.setOnClickListener { mPresenter!!.onRetryClick() }
    }

    override fun showLoadingScreen() {
        setGone(mArticleLayout, mErrorLayout)
        mProgressBar.setVisible()
    }

    override fun showNetworkToast() {
        Toast.makeText(context, context.getString(R.string.no_network_message), Toast.LENGTH_LONG).show()
    }

    override fun showArticleDetails() {
        setGone(mErrorLayout, mProgressBar)
        mArticleLayout.setVisible()
    }

    fun setPresenter(presenter: ArticleContract.Presenter) {
        mPresenter = presenter
    }

    override fun setDescription(description: String) {
        articleDescription.text = description
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
