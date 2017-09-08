/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.data_fetch.entity

import org.jsoup.Connection
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import pl.orbitemobile.wspolnoty.activities.home.HomePresenter
import pl.orbitemobile.wspolnoty.data.remote.Downloader
import pl.orbitemobile.wspolnoty.data.remote.Mapper


class MapperTest {

    var presenter: HomePresenter? = null

    val downloader = Downloader()
    val mapper = Mapper()
    val response = mock(Connection.Response::class.java)

    @Before
    fun setUp() {
    }

    @Test
    fun onRetryClickTest_noNetworkAvailable() {
        presenter!!.onRetryClick()
//        mapper.mapArticle(null)
//        Mockito.verify<HomeFragment>(homeFragment, Mockito.times(1)).showNetworkToast()
    }

}