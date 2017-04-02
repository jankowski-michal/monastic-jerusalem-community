/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.data_fetch.entity


import android.content.Context
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import org.junit.Before
import org.junit.Test
import pl.orbitemobile.kotler.Specification
import pl.orbitemobile.wspolnoty.activities.article.ArticleActivity
import pl.orbitemobile.wspolnoty.activities.contact.ContactActivity
import pl.orbitemobile.wspolnoty.activities.home.HomeFragment
import pl.orbitemobile.wspolnoty.activities.home.HomePresenter
import pl.orbitemobile.wspolnoty.activities.home.domain.HomeScreen
import pl.orbitemobile.wspolnoty.activities.hours.HoursActivity
import pl.orbitemobile.wspolnoty.activities.news.NewsActivity
import pl.orbitemobile.wspolnoty.activities.where.WhereActivity
import pl.orbitemobile.wspolnoty.data.entities.Article
import pl.orbitemobile.wspolnoty.utilities.ActivityLauncher
import pl.orbitemobile.wspolnoty.utilities.ConnectivityCheck

class HomePresenterTest : Specification() {

    lateinit var presenter: HomePresenter

    val homeFragment = HomeFragment::class.mock
    val context = Context::class.mock
    val connectivityCheck = ConnectivityCheck::class.mock
    val observer = MockedObserver()
    val useCase = HomeScreen::class.mock
    val activityUtility = ActivityLauncher::class.mock

    @Before
    fun setUp() {
        presenter = HomePresenter(homeFragment, context)
        sePtresenterMocks()
    }

    @Test
    fun onRetryClickTest_noNetworkAvailable() {
        Given
        setNetworkConnectionTo(false)

        When
        presenter.onRetryClick()

        Then
        homeFragment.onlyOnce().showNetworkToast()
    }

    @Test
    fun onRetryClickTestnetworkAvailable() {
        Given
        setNetworkConnectionTo(true)

        When
        presenter.onRetryClick()

        Then
        homeFragment.notEvenOnce().showNetworkToast()
        useCase.onlyOnce().remoteArticles
        homeFragment.onlyOnce().showLoadingScreen()
    }

    @Test
    fun onHoursButtonClickTest() {
        When
        presenter.onHoursButtonClick()
        activityUtility.onlyOnce().startActivity(context, HoursActivity::class.java)
    }

    @Test
    fun onWhereButtonClickTest() {
        When
        presenter.onWhereButtonClick()

        Then
        activityUtility.onlyOnce().startActivity(context, WhereActivity::class.java)
    }

    @Test
    fun onContactButtonClickTest() {
        When
        presenter.onContactButtonClick()

        Then
        activityUtility.onlyOnce().startActivity(context, ContactActivity::class.java)
    }

    @Test
    fun onNewsButtonClickTest() {
        When
        presenter.onNewsButtonClick()

        Then
        activityUtility.onlyOnce().startActivity(context, NewsActivity::class.java)
    }

    @Test
    fun onArticleClickTest() {
        val article = Article("the_title", "the_img_url", "the_article_url")
        val extra = hashMapOf(
                Article.KEY.ARTICLE_URL to article.articleUrl,
                Article.KEY.TITLE to article.title,
                Article.KEY.IMG_URL to article.imgUrl)
        presenter.onArticleClick(article)
        activityUtility.onlyOnce().startActivity(context, ArticleActivity::class.java, extra)
    }

    fun sePtresenterMocks() {
        presenter.mUseCase = useCase
        presenter.setConnectivityCheck(connectivityCheck)
        presenter.mArticlesRemoteObserver = observer
        presenter.activityUtility = activityUtility
    }


    fun setNetworkConnectionTo(value: Boolean) {
        connectivityCheck.isNetworkAvailable thenReturn value
        homeFragment.showNetworkToast() thenReturn true
        useCase.remoteArticles thenReturn object : Single<Array<Article>>() {
            override fun subscribeActual(observer: SingleObserver<in Array<Article>>) {
            }
        }
    }

    inner class MockedObserver : SingleObserver<Array<Article>> {
        override fun onSubscribe(@NonNull d: Disposable) {
        }

        override fun onError(e: Throwable) {
        }

        override fun onSuccess(@NonNull articles: Array<Article>) {
        }
    }

}
