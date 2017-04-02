/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.home

import pl.orbitemobile.wspolnoty.BasePresenter
import pl.orbitemobile.wspolnoty.BaseView
import pl.orbitemobile.wspolnoty.data.entities.Article

class HomeContract {

    interface View : BaseView<Presenter> {

        fun showLoadingScreen()

        fun showErrorMessage()

        fun setTodaysMass(content: String)

        fun showArticles(articles: Array<Article>)

        fun showNetworkToast(): Boolean
    }

    interface Presenter : BasePresenter {

        fun onHoursButtonClick()

        fun onWhereButtonClick()

        fun onContactButtonClick()

        fun onNewsButtonClick()

        fun onArticleClick(article: Article)

        fun onRetryClick()
    }
}
