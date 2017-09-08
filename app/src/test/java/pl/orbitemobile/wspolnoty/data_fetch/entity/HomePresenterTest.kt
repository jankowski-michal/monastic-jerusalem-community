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
import org.mockito.Mock
import org.mockito.Mockito.*
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

class HomePresenterTest {

    var presenter: HomePresenter? = null

    @Mock val homeFragment = mock(HomeFragment::class.java)!!
    @Mock val context = mock(Context::class.java)!!
    @Mock val connectivityCheck = mock(ConnectivityCheck::class.java)!!
    @Mock val observer = MockedObserver()
    @Mock val useCase = mock(HomeScreen::class.java)!!
    @Mock val activityUtility = mock(ActivityLauncher::class.java)!!

    @Before
    fun setUp() {
        presenter = HomePresenter(homeFragment, context)
        sePtresenterMocks()
    }

    @Test
    fun onRetryClickTest_noNetworkAvailable() {
        setNetworkConnectionTo(false)

        presenter!!.onRetryClick()
        verify<HomeFragment>(homeFragment, times(1)).showNetworkToast()
    }

    @Test
    fun onRetryClickTestnetworkAvailable() {
        setNetworkConnectionTo(true)

        presenter!!.onRetryClick()

        verify<HomeFragment>(homeFragment, times(0)).showNetworkToast()
        verify<HomeScreen>(useCase, times(1)).remoteArticles
        verify<HomeFragment>(homeFragment, times(1)).showLoadingScreen()
    }

    fun setNetworkConnectionTo(value: Boolean) {
        `when`(connectivityCheck!!.isNetworkAvailable).thenReturn(value)
        `when`(homeFragment!!.showNetworkToast()).thenReturn(true)
        `when`(useCase!!.remoteArticles).thenReturn(object : Single<Array<Article>>() {
            override fun subscribeActual(observer: SingleObserver<in Array<Article>>) {
            }
        })
    }

    @Test
    fun onHoursButtonClickTest() {
        presenter!!.onHoursButtonClick()
        verify<ActivityLauncher>(activityUtility, times(1)).startActivity(context, HoursActivity::class.java)
    }

    @Test
    fun onWhereButtonClickTest() {
        presenter!!.onWhereButtonClick()
        verify<ActivityLauncher>(activityUtility, times(1)).startActivity(context, WhereActivity::class.java)
    }

    @Test
    fun onContactButtonClickTest() {
        presenter!!.onContactButtonClick()
        verify<ActivityLauncher>(activityUtility, times(1)).startActivity(context, ContactActivity::class.java)
    }

    @Test
    fun onNewsButtonClickTest() {
        presenter!!.onNewsButtonClick()
        verify<ActivityLauncher>(activityUtility, times(1)).startActivity(context, NewsActivity::class.java)
    }

    @Test
    fun onArticleClickTest() {
        val article = Article("the_title", "the_img_url", "the_article_url")
        val extra = hashMapOf(
                Article.KEY.ARTICLE_URL to article.articleUrl,
                Article.KEY.TITLE to article.title,
                Article.KEY.IMG_URL to article.imgUrl)
        presenter!!.onArticleClick(article)
        verify<ActivityLauncher>(activityUtility, times(1)).startActivity(context, ArticleActivity::class.java, extra)
    }

    fun sePtresenterMocks() {
        presenter!!.mUseCase = useCase
        presenter!!.setConnectivityCheck(connectivityCheck)
        presenter!!.mArticlesRemoteObserver = observer
        presenter!!.activityUtility = activityUtility
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
