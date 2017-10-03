/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.home

import io.reactivex.SingleObserver
import pl.orbitemobile.mvp.MVP
import pl.orbitemobile.wspolnoty.activities.utils.DownloadView
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO

class HomeContract {

    abstract class View(layoutId: Int) : MVP.BaseView<Presenter>(layoutId), DownloadView {
        abstract fun setTodayMass(content: String)
        abstract fun showArticles(articles: Array<ArticleDTO>)
    }

    abstract class Presenter : MVP.BasePresenter<View>(), SingleObserver<Array<ArticleDTO>> {
        abstract fun onHoursButtonClick()
        abstract fun onWhereButtonClick()
        abstract fun onContactButtonClick()
        abstract fun onNewsButtonClick()
        abstract fun onArticleClick(article: ArticleDTO)
        abstract fun onRetryClick()
        abstract fun onWordButtonClick()
    }
}
