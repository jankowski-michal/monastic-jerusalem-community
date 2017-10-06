/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.home

import io.reactivex.Single
import io.reactivex.SingleObserver
import pl.orbitemobile.mvp.MvpPresenter
import pl.orbitemobile.mvp.MvpView
import pl.orbitemobile.wspolnoty.activities.mvp.DownloadView
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO

class HomeContract {

    abstract class View(layoutId: Int) : MvpView<Presenter>(layoutId), DownloadView {
        abstract fun setTodayMass(content: String)
        abstract fun showArticles(articles: Array<ArticleDTO>)
    }

   interface Presenter : MvpPresenter<View>, SingleObserver<Array<ArticleDTO>> {
       fun onHoursButtonClick()
       fun onWhereButtonClick()
       fun onContactButtonClick()
       fun onNewsButtonClick()
       fun onArticleClick(article: ArticleDTO)
       fun onRetryClick()
       fun onWordButtonClick()
    }

    interface UseCase {
       val remoteArticles: Single<Array<ArticleDTO>>
    }
}
