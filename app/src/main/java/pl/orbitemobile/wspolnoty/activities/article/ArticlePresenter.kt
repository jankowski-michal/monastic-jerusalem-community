/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.article

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.activities.article.domain.ArticleScreen
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO
import pl.orbitemobile.wspolnoty.utilities.DialogBuilder
import pl.orbitemobile.wspolnoty.utilities.isNetwork

class ArticlePresenter : ArticleContract.Presenter() {

    val mUseCase: ArticleScreen = ArticleScreen() //todo: add composite disposable to both presenters here and in mvp package
    val ARTICLE_URL = "ARTICLE_URL"

    var mArticleUrl: String? = null

    override fun onViewAttached() {
        mView?.showLoadingScreen()
        setArticleUrl()
        issueDataDownloadIfNetworkIsAvailable()
    }

    private fun setArticleUrl() {
        val intent = mView?.getIntent()
        if (intent?.hasArticleUrl() == true) {
            intent.setArticleUrl()
        }
    }

    override fun onStart(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.about_app) {
            DialogBuilder().showAboutDialog(mView?.context)
            return true
        }
        return false
    }

    override fun onRetryClick() = issueDataDownloadIfNetworkIsAvailable()

    override fun onShowWebsiteClick() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mArticleUrl))
        mView?.getContext()?.startActivity(browserIntent)
    }

    private fun issueDataDownloadIfNetworkIsAvailable() {
        if (isNetwork()) {
            mView?.showLoadingScreen()
            issueDataDownload()
        } else {
            mView?.showErrorMessage()
            mView?.showNetworkToast()
        }
    }

    private fun issueDataDownload() {
        val articleUrl = mArticleUrl
        if (articleUrl != null) {
            mUseCase.getRemoteArticleDetails(articleUrl).subscribe(this)
        } else {
            // todo: article url is null
        }
    }

    private fun Intent.hasArticleUrl(): Boolean = hasExtra(ARTICLE_URL)

    private fun Intent.setArticleUrl() {
        mArticleUrl = getStringExtra(ARTICLE_URL)
    }

    override fun onError(e: Throwable) {
        mView?.showErrorMessage() //todo: does this still work with rxUtils?
        e.printStackTrace()
    }

    override fun onSuccess(article: ArticleDTO) {
        mView?.showArticleDetails(article)
    }

}
