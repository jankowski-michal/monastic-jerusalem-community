/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.article

import android.content.Intent
import android.net.Uri
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import pl.orbitemobile.wspolnoty.activities.article.domain.ArticleUseCase
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO

class ArticlePresenter(override var view: ArticleContract.View? = null,
                       override var disposable: CompositeDisposable? = CompositeDisposable()) : ArticleContract.Presenter {

    private val useCase: ArticleContract.UseCase = ArticleUseCase()
    private val ARTICLE_URL = "ARTICLE_URL"

    var articleUrl: String? = null

    override fun onViewAttached() {
        view?.showLoadingScreen()
        setArticleUrl()
        issueDataDownloadIfNetworkIsAvailable()
    }

    private fun setArticleUrl() {
        val intent = view?.getIntent()
        if (intent?.hasArticleUrl() == true) {
            intent.setArticleUrl()
        }
    }

    override fun onRetryClick() = issueDataDownloadIfNetworkIsAvailable()

    override fun onShowWebsiteClick() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(articleUrl))
        view?.context?.startActivity(browserIntent)
    }

    private fun issueDataDownloadIfNetworkIsAvailable() {
        if (isNetwork()) {
            view?.showLoadingScreen()
            issueDataDownload()
        } else {
            view?.showErrorMessage()
            view?.showNetworkToast()
        }
    }

    private fun issueDataDownload() {
        val articleUrl = articleUrl
        if (articleUrl != null) {
            useCase.getRemoteArticleDetails(articleUrl).subscribe(this)
        }
    }

    private fun Intent.hasArticleUrl(): Boolean = hasExtra(ARTICLE_URL)

    private fun Intent.setArticleUrl() {
        articleUrl = getStringExtra(ARTICLE_URL)
    }

    override fun onSubscribe(d: Disposable) {
        disposable?.add(d)
    }

    override fun onError(e: Throwable) {
        view?.showErrorMessage()
        e.printStackTrace()
    }

    override fun onSuccess(article: ArticleDTO) {
        view?.showArticleDetails(article)
    }

}
