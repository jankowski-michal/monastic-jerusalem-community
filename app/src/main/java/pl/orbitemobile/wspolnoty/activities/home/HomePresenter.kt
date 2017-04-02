/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.home

import android.os.Bundle
import android.os.PersistableBundle
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import pl.orbitemobile.wspolnoty.activities.article.ArticleActivity
import pl.orbitemobile.wspolnoty.activities.contact.ContactActivity
import pl.orbitemobile.wspolnoty.activities.home.domain.HomeUseCase
import pl.orbitemobile.wspolnoty.activities.hours.HoursActivity
import pl.orbitemobile.wspolnoty.activities.news.NewsActivity
import pl.orbitemobile.wspolnoty.activities.where.WhereActivity
import pl.orbitemobile.wspolnoty.activities.word.WordActivity
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO
import pl.orbitemobile.wspolnoty.utilities.ActivityLauncher
import pl.orbitemobile.wspolnoty.utilities.TodaysMassCalculator

class HomePresenter(override var disposable: CompositeDisposable? = CompositeDisposable(),
                    override var view: HomeContract.View? = null) : HomeContract.Presenter {

    private var useCase: HomeContract.UseCase = HomeUseCase()

    private var activityUtility = ActivityLauncher()

    override fun onViewAttached() {
        setTodaysMassTimes()
    }


    override fun onRetryClick() {
        if (isNetwork()) {
            useCase.remoteArticles.subscribe(this)
            view?.showLoadingScreen()
        } else {
            view?.showNetworkToast()
        }
    }

    override fun onStart(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        useCase.remoteArticles.subscribe(this)
    }

    override fun onHoursButtonClick() =
            activityUtility.startActivity(view?.context, HoursActivity::class.java)


    override fun onWordButtonClick() = activityUtility.startActivity(view?.context, WordActivity::class.java)


    override fun onWhereButtonClick() = activityUtility.startActivity(view?.context, WhereActivity::class.java)


    override fun onContactButtonClick() = activityUtility.startActivity(view?.context, ContactActivity::class.java)


    override fun onNewsButtonClick() = activityUtility.startActivity(view?.context, NewsActivity::class.java)


    override fun onArticleClick(article: ArticleDTO) {
        val extra = hashMapOf(
                ArticleDTO.KEY.ARTICLE_URL to article.articleUrl,
                ArticleDTO.KEY.TITLE to article.title,
                ArticleDTO.KEY.IMG_URL to article.imgUrl)
        activityUtility.startActivity(view?.context, ArticleActivity::class.java, extra)
    }

    private fun setTodaysMassTimes() {
        val todaysMassCalculator = TodaysMassCalculator()
        val todays_mass = todaysMassCalculator.calculateTodaysMass()
        view?.setTodayMass(todays_mass)
    }

    override fun onError(e: Throwable) {
        view?.showErrorMessage()
        e.printStackTrace()
    }

    override fun onSuccess(articles: Array<ArticleDTO>) {
        view?.showArticles(articles)
    }

    override fun onSubscribe(d: Disposable) {
        disposable?.addAll(d)
    }
}
