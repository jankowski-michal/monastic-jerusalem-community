/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.article

import android.content.Intent
import io.reactivex.Single
import io.reactivex.SingleObserver
import pl.orbitemobile.mvp.MvpPresenter
import pl.orbitemobile.mvp.MvpView
import pl.orbitemobile.wspolnoty.activities.mvp.DownloadView
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO

interface ArticleContract {

    abstract class View(layoutId: Int) : MvpView<Presenter>(layoutId), DownloadView { //fixme: download view should extend MVPView
        abstract fun showArticleDetails(article: ArticleDTO)
        abstract fun getIntent(): Intent
    }

    interface Presenter : MvpPresenter<View>, SingleObserver<ArticleDTO> {
        fun onRetryClick()
        fun onShowWebsiteClick()
    }

    interface UseCase {
        fun getRemoteArticleDetails(articleDetailsUrl: String): Single<ArticleDTO>
    }
}