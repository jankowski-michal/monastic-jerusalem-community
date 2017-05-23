/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.data.remote

import org.jsoup.Connection
import org.jsoup.Jsoup
import java.io.IOException

open class Downloader {

    private val USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36"

    private val TIMEOUT = 10000

    private val TAG = Downloader::class.java.simpleName

    fun getResponse(url: String): Connection.Response? {
        try {
            return Jsoup.connect(url)
                    .userAgent(USER_AGENT)
                    .timeout(TIMEOUT)
                    .ignoreContentType(true)
                    .execute()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }

}
