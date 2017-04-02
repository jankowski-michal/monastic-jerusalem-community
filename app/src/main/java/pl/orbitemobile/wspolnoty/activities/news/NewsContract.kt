/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.news

import io.reactivex.Single
import io.reactivex.SingleObserver
import pl.orbitemobile.mvp.MvpPresenter
import pl.orbitemobile.mvp.MvpView
import pl.orbitemobile.wspolnoty.activities.mvp.DownloadView
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO

class NewsContract {

    abstract class View(layoutId: Int) : MvpView<Presenter>(layoutId), DownloadView {
        abstract fun showArticles(articleDTOs: Array<ArticleDTO>)
    }

    interface Presenter : MvpPresenter<View>, SingleObserver<Array<ArticleDTO>> {
        fun onRetryClick()
        fun onArticleClick(articleDTO: ArticleDTO)
        fun onShowMore()
    }

    interface UseCase {
       fun getRemoteArticles(page: Int): Single<Array<ArticleDTO>>
    }
}
