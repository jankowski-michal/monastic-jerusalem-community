/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.news

import android.content.Intent
import android.util.Log
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import pl.orbitemobile.wspolnoty.activities.article.ArticleActivity
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO

class NewsPresenter(override var view: NewsContract.View? = null,
                    override var disposable: CompositeDisposable? = CompositeDisposable()) : NewsContract.Presenter {

    private val useCase: NewsContract.UseCase = NewsUseCase()

    private val TAG = NewsPresenter::class.java.simpleName

    private val FIRST_PAGE = 1

    private var page: Int = FIRST_PAGE

    override fun onViewAttached() {
        view?.showLoadingScreen()
        useCase.getRemoteArticles(page).subscribe(this)
    }

    override fun onRetryClick() {
        if (isNetwork()) {
            view?.showLoadingScreen()
            useCase.getRemoteArticles(page).subscribe(this)
        } else {
            view?.showNetworkToast()
        }
    }

    override fun onArticleClick(articleDTO: ArticleDTO) {
        val intent = Intent(view?.context, ArticleActivity::class.java)
        intent.putExtra(ArticleDTO.KEY.ARTICLE_URL.name, articleDTO.articleUrl)
        intent.putExtra(ArticleDTO.KEY.TITLE.name, articleDTO.title)
        intent.putExtra(ArticleDTO.KEY.IMG_URL.name, articleDTO.imgUrl)
        view?.context?.startActivity(intent)
    }

    override fun onShowMore() {
        page += 1
        view?.showLoadingScreen()
        useCase.getRemoteArticles(page).subscribe(this)
    }

    override fun onError(e: Throwable) {
        view?.showErrorMessage()
        Log.d(TAG, "onError")
        e.printStackTrace()
    }

    override fun onSuccess(@NonNull articleDTOs: Array<ArticleDTO>) {
        view?.showArticles(articleDTOs)
    }


    override fun onSubscribe(d: Disposable) {
        disposable?.addAll(d)
    }
}


