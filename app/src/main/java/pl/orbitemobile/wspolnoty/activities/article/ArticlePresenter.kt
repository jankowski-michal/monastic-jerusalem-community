/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.article

import android.content.Intent
import android.view.MenuItem
import io.reactivex.SingleObserver
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.activities.article.domain.ArticleScreen
import pl.orbitemobile.wspolnoty.data.entities.Article
import pl.orbitemobile.wspolnoty.mvp.MVP
import pl.orbitemobile.wspolnoty.utilities.ConnectivityCheck
import pl.orbitemobile.wspolnoty.utilities.DialogBuilder

class ArticlePresenter :
        MVP.BasePresenter<ArticleContract.View>(),
        ArticleContract.Presenter {

     //todo: test fully using when, tne etc like Igor

    val mArticleRemoteObserver = ArticleRemoteObserver()
    val mUseCase: ArticleScreen = ArticleScreen()
    val mDisposable = CompositeDisposable() //todo: add composite disposable to both presenters here and in mvp package
    val ARTICLE_URL = "ARTICLE_URL"

    var mArticleUrl: String? = null
    var mConnectivityCheck: ConnectivityCheck? = null

    override fun onViewCreated() {
        mView?.showLoadingScreen()
        setArticleUrl()
        initConnectivityCheck()
        issueDataDownloadIfNetworkIsAvailable()
    }

    fun setArticleUrl() {
        val intent = mView?.getIntent()
        if (intent?.hasArticleUrl() ?: false) {
            intent?.setArticleUrl()
        }
    }

    fun initConnectivityCheck() {
        mConnectivityCheck = ConnectivityCheck(mView?.getContext())
    }

    override fun onStop() {
        mDisposable.clear()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.about_app) {
            DialogBuilder().showAboutDialog(mView?.getContext())
            return true
        }
        return false
    }

    override fun onRetryClick() = issueDataDownloadIfNetworkIsAvailable()

    private fun issueDataDownloadIfNetworkIsAvailable() {
        if (isNetworkAvailable()) {
            mView?.showLoadingScreen()
            issueDataDownload()
        } else {
            mView?.showNetworkToast()
        }
    }

    private fun issueDataDownload() {
        val articleUrl = mArticleUrl
        if (articleUrl != null) {
            mUseCase.getRemoteArticleDetails(articleUrl).subscribe(mArticleRemoteObserver)
        } else {
            // todo: article url is null articleUrl
        }
    }

    private fun isNetworkAvailable() = mConnectivityCheck?.isNetworkAvailable ?: false

    private fun Intent.hasArticleUrl(): Boolean = hasExtra(ARTICLE_URL)

    private fun Intent.setArticleUrl() {
        mArticleUrl = getStringExtra(ARTICLE_URL)
    }

    fun setArticleInView(article: Article) {
        mView?.setImgUrl(article.imgUrl)
        mView?.setTitle(article.title)
        mView?.setDescription(article.description!!)
        mView?.showArticleDetails()

    }

    inner class ArticleRemoteObserver : SingleObserver<Article> {

        override fun onSubscribe(@NonNull d: Disposable) {
            mDisposable.add(d)
        }

        override fun onError(e: Throwable) {
            mView?.showErrorMessage()
            e.printStackTrace()
        }

        override fun onSuccess(@NonNull article: Article) = setArticleInView(article)
    }
}
