/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.news

import io.reactivex.SingleObserver
import pl.orbitemobile.mvp.MVP
import pl.orbitemobile.wspolnoty.activities.article.ArticleContract
import pl.orbitemobile.wspolnoty.activities.utils.DownloadView
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO

class NewsContract {

    abstract class View(layoutId: Int) : MVP.BaseView<Presenter>(layoutId), DownloadView {
        abstract fun showArticles(articleDTOs: Array<ArticleDTO>)
    }

    abstract class Presenter : MVP.BasePresenter<View>(), SingleObserver<Array<ArticleDTO>> {
        abstract fun onRetryClick()
        abstract fun onArticleClick(articleDTO: ArticleDTO)
        abstract fun onShowMore()
    }
}
