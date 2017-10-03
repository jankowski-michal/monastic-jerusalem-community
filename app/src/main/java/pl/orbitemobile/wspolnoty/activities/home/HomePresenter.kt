/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.home

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.activities.article.ArticleActivity
import pl.orbitemobile.wspolnoty.activities.contact.ContactActivity
import pl.orbitemobile.wspolnoty.activities.home.domain.HomeScreen
import pl.orbitemobile.wspolnoty.activities.hours.HoursActivity
import pl.orbitemobile.wspolnoty.activities.news.NewsActivity
import pl.orbitemobile.wspolnoty.activities.where.WhereActivity
import pl.orbitemobile.wspolnoty.activities.word.WordActivity
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO
import pl.orbitemobile.wspolnoty.utilities.*

class HomePresenter : HomeContract.Presenter() {

    var mUseCase = HomeScreen()

    var activityUtility = ActivityLauncher()

    override fun onViewAttached() {
        setTodaysMassTimes()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemThatWasClickedId = item.itemId
        if (itemThatWasClickedId == R.id.about_app) {
            val builder = DialogBuilder()
            builder.showAboutDialog(mView?.context)
            return true
        }
        return false
    }

    override fun onRetryClick() {
        if (isNetwork()) {
            mUseCase.remoteArticles.subscribe(this)
            mView?.showLoadingScreen()
        } else {
            mView?.showNetworkToast()
        }
    }

    override fun onStart(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        mUseCase.remoteArticles.subscribe(this)
    }

    override fun onStop() {
        mDisposable.dispose()
        mDisposable.clear()
    }

    override fun onHoursButtonClick() =
            activityUtility.startActivity(mView?.context, HoursActivity::class.java)


    override fun onWordButtonClick() = activityUtility.startActivity(mView?.context, WordActivity::class.java)


    override fun onWhereButtonClick() = activityUtility.startActivity(mView?.context, WhereActivity::class.java)


    override fun onContactButtonClick() = activityUtility.startActivity(mView?.context, ContactActivity::class.java)


    override fun onNewsButtonClick() = activityUtility.startActivity(mView?.context, NewsActivity::class.java)


    override fun onArticleClick(article: ArticleDTO) {
        val extra = hashMapOf(
                ArticleDTO.KEY.ARTICLE_URL to article.articleUrl,
                ArticleDTO.KEY.TITLE to article.title,
                ArticleDTO.KEY.IMG_URL to article.imgUrl)
        activityUtility.startActivity(mView?.context, ArticleActivity::class.java, extra)
    }

    private fun setTodaysMassTimes() {
        val todaysMassCalculator = TodaysMassCalculator()
        val todays_mass = todaysMassCalculator.calculateTodaysMass()
        mView?.setTodayMass(todays_mass)
    }

    override fun onError(e: Throwable) {
        mView?.showErrorMessage()
        e.printStackTrace()
    }

    override fun onSuccess(articles: Array<ArticleDTO>) {
        mView?.showArticles(articles)
    }


}
