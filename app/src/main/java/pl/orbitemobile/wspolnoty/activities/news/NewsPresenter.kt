/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.news

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MenuItem
import io.reactivex.annotations.NonNull
import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.activities.article.ArticleActivity
import pl.orbitemobile.wspolnoty.activities.news.domain.NewsScreen
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO
import pl.orbitemobile.wspolnoty.utilities.DialogBuilder
import pl.orbitemobile.wspolnoty.utilities.isNetwork

class NewsPresenter : NewsContract.Presenter() {

    private val mUseCase: NewsScreen = NewsScreen()


    private val TAG = NewsPresenter::class.java.simpleName

    private val FIRST_PAGE = 1


    private var page: Int = FIRST_PAGE

    override fun onViewAttached() {
        mView?.showLoadingScreen()
        mUseCase.getRemoteArticles(page).subscribe(this)
    }

    override fun onStart(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemThatWasClickedId = item.itemId
        if (itemThatWasClickedId == R.id.about_app) {
            val builder = DialogBuilder()
            builder.showAboutDialog(mView?.context) //fixme - move to view
            return true
        }
        return false
    }

    override fun onRetryClick() {
        if (isNetwork()) {
            mView?.showLoadingScreen()
            mUseCase.getRemoteArticles(page).subscribe(this)
        } else {
            mView?.showNetworkToast()
        }
    }

    override fun onArticleClick(articleDTO: ArticleDTO) {
        val intent = Intent(mView?.context, ArticleActivity::class.java)
        intent.putExtra(ArticleDTO.KEY.ARTICLE_URL.name, articleDTO.articleUrl)
        intent.putExtra(ArticleDTO.KEY.TITLE.name, articleDTO.title)
        intent.putExtra(ArticleDTO.KEY.IMG_URL.name, articleDTO.imgUrl)
        mView?.context?.startActivity(intent)
    }

    override fun onShowMore() {
        page += 1
        mView?.showLoadingScreen()
        mUseCase.getRemoteArticles(page).subscribe(this)
    }

    override fun onError(e: Throwable) {
        mView?.showErrorMessage()
        Log.d(TAG, "onError")
        e.printStackTrace()
    }

    override fun onSuccess(@NonNull articleDTOs: Array<ArticleDTO>) {
        mView?.showArticles(articleDTOs)
    }

}


