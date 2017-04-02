package pl.orbitemobile.wspolnoty.data.remote.mapper

import org.jsoup.Connection

class HoursMapper {
    companion object {
        val instance = HoursMapper()
    }

    fun mapHours(response: Connection.Response): String {
        val sidebarWidget = response.parse().getElementsByAttributeValue("class", "widget widget_text")
                .first { it.text().contains("Godziny sprawowania oficjów") }
        return sidebarWidget.html()

    }
}