package pl.orbitemobile.wspolnoty.data.remote.mapper

import org.jsoup.Connection
import pl.orbitemobile.wspolnoty.data.dto.ReadingDTO

class TodayReadingsMapper private constructor() {
    companion object {
        val instance = TodayReadingsMapper()
    }

    fun mapReadingsForDay(response: Connection.Response): List<ReadingDTO> {
        val page = response.parse().getElementsByAttributeValue("class", "tab-content liturgia-content").first()
        val readingTabs = page.getElementsByAttributeValue("class", "tab-pane fade ").toMutableList()
        return readingTabs.map { readingTab ->
            val html = readingTab.html()
            val from = html.substringBefore("</h2>").replace("<h2>", "").trim()
            val heading = html.substringAfter("</h2>").substringBefore("</h4>").replace("<h4>", "").replace("<em>", "").replace("</em>", "").trim()
            val content = html.substringAfter("</h4>")
            ReadingDTO(from, heading, content)
        }
    }
}