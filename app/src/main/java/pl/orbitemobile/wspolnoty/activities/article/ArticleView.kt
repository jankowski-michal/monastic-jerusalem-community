/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.article

import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import pl.orbitemobile.mvp.bind
import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.activities.utils.DownloadViewUtil
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO

class ArticleView : ArticleContract.View(R.layout.article_view) {

    override var viewContent: View? = null
    override var progressBar: View? = null
    override var errorLayout: LinearLayout? = null
    override var errorButton: TextView? = null

    lateinit var articleTitle: TextView
    lateinit var articleDescription: TextView
    lateinit var appbarCollapsingImage: ImageView
    lateinit var showArticleButton: TextView

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

    override fun showNetworkToast() = DownloadViewUtil.showNetworkToast(context)

    override fun showErrorMessage() =
            DownloadViewUtil.showErrorMessage(this) { mPresenter!!.onRetryClick() }


    override fun showLoadingScreen() = DownloadViewUtil.showLoadingScreen(this)

    override fun showArticleDetails(article: ArticleDTO) {
        setTitle(article.title)
        setDescription(article.description!!)
        setImgUrl(article.imgUrl)
        DownloadViewUtil.showViewContent(this)
        showArticleButton.setOnClickListener { mPresenter!!.onShowWebsiteClick() }
    }

    private fun setDescription(description: String) {
        articleDescription.movementMethod = LinkMovementMethod.getInstance()
        articleDescription.text = SpannableString(fromHtml(description))
    }

    @SuppressWarnings("deprecation") // todo: move somewhere else and use probably on all mapped texts
    fun fromHtml(html: String): Spanned {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            Html.fromHtml(html)
        }
    }

    private fun setTitle(title: String) {
        articleTitle.text = title
    }

    private fun setImgUrl(imgUrl: String) {
        Picasso.with(context)
                .load(imgUrl)
                .into(appbarCollapsingImage)
    }

    override fun getIntent() = activity.intent

}
