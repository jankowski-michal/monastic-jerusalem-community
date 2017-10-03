/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.article

import android.content.Intent
import io.reactivex.SingleObserver
import pl.orbitemobile.mvp.MVP
import pl.orbitemobile.wspolnoty.activities.utils.DownloadView
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO

class ArticleContract {

    abstract class View(layoutId: Int) : MVP.BaseView<Presenter>(layoutId), DownloadView {
        abstract fun showArticleDetails(article: ArticleDTO)
        abstract fun getIntent(): Intent
    }

    abstract class Presenter : MVP.BasePresenter<View>(), SingleObserver<ArticleDTO> {
        abstract fun onRetryClick()
        abstract fun onShowWebsiteClick()
    }
}
