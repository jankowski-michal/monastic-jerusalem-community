/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.article

import android.content.Intent
import pl.orbitemobile.mvp.MVP

class ArticleContract {

    interface View : MVP.MVPView {

        fun setDescription(description: String)

        fun setTitle(title: String)

        fun setImgUrl(imgUrl: String)

        fun showErrorMessage()

        fun showLoadingScreen()

        fun showArticleDetails()

        fun showNetworkToast()

        fun getIntent(): Intent
    }

    interface Presenter : MVP.Presenter {

        fun onRetryClick()

        fun onShowWebsiteClick()
    }
}
