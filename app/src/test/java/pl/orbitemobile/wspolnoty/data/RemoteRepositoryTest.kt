/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 *//*


package pl.orbitemobile.wspolnoty.data

import io.reactivex.observers.TestObserver
import org.jsoup.Connection
import org.junit.Before
import org.junit.Test
import pl.orbitemobile.kotler.Specification
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO
import pl.orbitemobile.wspolnoty.data.remote.download.DownloadManagerImpl
import pl.orbitemobile.wspolnoty.data.remote.mapper.MapperImpl
import java.io.IOException

class RemoteRepositoryTest : Specification() {

    lateinit var repository: RemoteRepositoryImpl
    lateinit var downloaderMock: DownloadManagerImpl
    lateinit var mapperMock: MapperImpl

    val exception = IOException()
    val mappedArticles = arrayOf<ArticleDTO>()
    val response = Connection.Response::class.mock
    val article = ArticleDTO("", "", "")
    val articleUrl = ""
    val page = 1

    @Before
    fun setUp() {
        repository = RemoteRepositoryImpl()
        downloaderMock = mock()
        mapperMock = mock()
        repository.mapper = mapperMock
        repository.downloader = downloaderMock
    }

    @Test
    fun getArticles() {
        Given
        var observer = TestObserver<Array<ArticleDTO>>()

        And
        downloaderMock.getResponse(repository.HOMEPAGE) thenThrow exception

        When
        repository.getArticles().subscribe(observer)

        Then
        observer.assertError(exception)

        Given
        observer = TestObserver()
        downloaderMock = mock()
        repository.downloader = downloaderMock

        And
        downloaderMock.getResponse(repository.HOMEPAGE) thenReturn response
        mapperMock.mapArticles(response) thenReturn mappedArticles

        When
        repository.getArticles().subscribe(observer)

        Then
        observer.verify {
            assertResult(mappedArticles)
            assertComplete()
        }
    }

    @Test
    fun getArticleDetails() {
        Given
        var observer = TestObserver<ArticleDTO>()

        And
        downloaderMock.getResponse(articleUrl) thenThrow exception

        When
        repository.getArticleDetails(articleUrl).subscribe(observer)

        Then
        observer.assertError(exception)

        Given
        observer = TestObserver()
        downloaderMock = mock()
        repository.downloader = downloaderMock

        And
        downloaderMock.getResponse(articleUrl) thenReturn response
        mapperMock.mapArticle(response, articleUrl) thenReturn article

        When
        repository.getArticleDetails(articleUrl).subscribe(observer)

        Then
        observer.verify {
            assertResult(article)
            assertComplete()
        }
    }

    @Test
    fun getNews() {
        Given
        var observer = TestObserver<Array<ArticleDTO>>()
        val pageUrl = repository.NEWS + page

        And
        downloaderMock.getResponse(pageUrl) thenThrow exception

        When
        repository.getNews(page).subscribe(observer)

        Then
        observer.assertError(exception)

        Given
        observer = TestObserver()
        downloaderMock = mock()
        repository.downloader = downloaderMock

        And
        downloaderMock.getResponse(pageUrl) thenReturn response
        mapperMock.mapNews(response) thenReturn mappedArticles

        When
        repository.getNews(page).subscribe(observer)

        Then
        observer.verify {
            assertResult(mappedArticles)
            assertComplete()
        }
    }

}*/
