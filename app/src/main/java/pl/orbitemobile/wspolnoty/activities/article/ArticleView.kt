/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.article

import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import pl.orbitemobile.mvp.bind
import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.activities.mvp.DownloadViewUtil
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO
import pl.orbitemobile.wspolnoty.data.remote.parser.Parser

class ArticleView : ArticleContract.View(R.layout.article_view) {

    override var viewContent: View? = null
    override var progressBar: View? = null
    override var errorLayout: LinearLayout? = null
    override var errorButton: TextView? = null

    private lateinit var articleTitle: TextView
    private lateinit var articleDescription: TextView
    lateinit var appbarCollapsingImage: ImageView
    private lateinit var showArticleButton: TextView

    private val parser = Parser.instance

    override fun View.bindViews(): View {
        articleTitle = bind(R.id.article_title)
        articleDescription = bind(R.id.article_description)
        progressBar = bind(R.id.progress_bar)
        errorLayout = bind(R.id.error_layout)
        errorButton = bind(R.id.error_button)
        viewContent = bind(R.id.article_layout)
        showArticleButton = bind(R.id.show_article_button)
        appbarCollapsingImage = activity.bind(R.id.appbar_collapsing_image)
        return this
    }

    override fun showErrorMessage() = DownloadViewUtil.showErrorMessage(this) { presenter!!.onRetryClick() }

    override fun showLoadingScreen() = DownloadViewUtil.showLoadingScreen(this)

    override fun showArticleDetails(article: ArticleDTO) {
        displayArticle(article)
        DownloadViewUtil.showViewContent(this)
        showArticleButton.setOnClickListener { presenter!!.onShowWebsiteClick() }
    }


    private fun displayArticle(article: ArticleDTO) {
        articleTitle.text = article.title
        articleDescription.movementMethod = LinkMovementMethod.getInstance()
        articleDescription.text = parser.fromHtml(article.description)
        Picasso.with(context)
                .load(article.imgUrl)
                .into(appbarCollapsingImage)
    }

    override fun getIntent() = activity.intent

}
