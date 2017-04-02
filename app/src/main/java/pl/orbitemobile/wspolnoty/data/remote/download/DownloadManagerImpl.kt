/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.data.remote.download

import org.jsoup.Connection
import org.jsoup.Jsoup

class DownloadManagerImpl private constructor() : DownloadManager {

    private val USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36"

    private val TIMEOUT = 10000

    override fun getResponse(url: String): Connection.Response {
        return Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .timeout(TIMEOUT)
                .ignoreContentType(true)
                .execute()
    }

    companion object {
        val instance = DownloadManagerImpl()
    }

}
