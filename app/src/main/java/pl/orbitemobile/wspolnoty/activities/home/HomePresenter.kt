/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.home

import android.content.Context
import android.view.MenuItem
import io.reactivex.SingleObserver
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.activities.article.ArticleActivity
import pl.orbitemobile.wspolnoty.activities.contact.ContactActivity
import pl.orbitemobile.wspolnoty.activities.home.domain.HomeScreen
import pl.orbitemobile.wspolnoty.activities.hours.HoursActivity
import pl.orbitemobile.wspolnoty.activities.news.NewsActivity
import pl.orbitemobile.wspolnoty.activities.where.WhereActivity
import pl.orbitemobile.wspolnoty.data.entities.Article
import pl.orbitemobile.wspolnoty.utilities.ActivityLauncher
import pl.orbitemobile.wspolnoty.utilities.ConnectivityCheck
import pl.orbitemobile.wspolnoty.utilities.DialogBuilder
import pl.orbitemobile.wspolnoty.utilities.TodaysMassCalculator

class HomePresenter(val mView: HomeContract.View, val mContext: Context) : HomeContract.Presenter {

    var mArticlesRemoteObserver: SingleObserver<Array<Article>> = ArticlesRemoteObserver()

    var mDisposable = CompositeDisposable()

    var mUseCase = HomeScreen()

    var mConnectivityCheck: ConnectivityCheck = ConnectivityCheck(mContext)

    var activityUtility = ActivityLauncher()

    override fun onViewCreated() {
        setTodaysMassTimes()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemThatWasClickedId = item.itemId
        if (itemThatWasClickedId == R.id.about_app) {
            val builder = DialogBuilder()
            builder.showAboutDialog(mContext)
            return true
        }
        return false
    }

    override fun onRetryClick() {
        if (mConnectivityCheck!!.isNetworkAvailable) {
            mUseCase.remoteArticles.subscribe(mArticlesRemoteObserver)
            mView.showLoadingScreen()
        } else {
            mView.showNetworkToast()
        }
    }

    override fun start() {
        mUseCase.remoteArticles.subscribe(mArticlesRemoteObserver)
    }

    override fun stop() {
        mDisposable.dispose()
        mDisposable.clear()
    }

    override fun onHoursButtonClick() {
        activityUtility.startActivity(mContext, HoursActivity::class.java)
    }

    override fun onWhereButtonClick() {
        activityUtility.startActivity(mContext, WhereActivity::class.java)
    }

    override fun onContactButtonClick() {
        activityUtility.startActivity(mContext, ContactActivity::class.java)
    }

    override fun onNewsButtonClick() {
        activityUtility.startActivity(mContext, NewsActivity::class.java)
    }

    override fun onArticleClick(article: Article) {
        val extra = hashMapOf(
                Article.KEY.ARTICLE_URL to article.articleUrl,
                Article.KEY.TITLE to article.title,
                Article.KEY.IMG_URL to article.imgUrl)
        activityUtility.startActivity(mContext, ArticleActivity::class.java, extra)
        //todo: use presenters method to start activity
    }

    private fun setTodaysMassTimes() {
        val todaysMassCalculator = TodaysMassCalculator()
        val todays_mass = todaysMassCalculator.calculateTodaysMass()
        mView.setTodaysMass(todays_mass)
    }

    fun setConnectivityCheck(mConnectivityCheck: ConnectivityCheck) {
        this.mConnectivityCheck = mConnectivityCheck
    }

    private inner class ArticlesRemoteObserver : SingleObserver<Array<Article>> {

        override fun onSubscribe(@NonNull d: Disposable) {
            mDisposable.add(d)
        }

        override fun onError(e: Throwable) {
            mView.showErrorMessage()
            e.printStackTrace()
        }

        override fun onSuccess(@NonNull articles: Array<Article>) {
            mView.showArticles(articles)
        }
    }
}
