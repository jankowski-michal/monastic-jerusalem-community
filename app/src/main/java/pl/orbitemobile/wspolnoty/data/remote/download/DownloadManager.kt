package pl.orbitemobile.wspolnoty.data.remote.download

import org.jsoup.Connection

interface DownloadManager {
    fun getResponse(url: String): Connection.Response
}