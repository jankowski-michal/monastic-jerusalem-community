package pl.orbitemobile.wspolnoty.data.remote.download

import java.util.*

class Urls {
//     http://niezbednik.niedziela.pl/liturgia/2017-10-05

    companion object {
        val HOMEPAGE = "http://wspolnoty-jerozolimskie.pl/"
        val NEWS = "http://wspolnoty-jerozolimskie.pl/category/aktualnosci/page/"
        val READINGS_FOR_TODAY
                get()= "http://niezbednik.niedziela.pl/liturgia/${today()}"
        val BIBLE_SEARCH = "https://wbiblii.pl/szukaj/"

        private fun today(): String {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val day = cal.get(Calendar.DAY_OF_MONTH).formatDateString()
            val month = cal.get(Calendar.MONTH).formatDateString()
            return "$year-$month-$day"
        }

        fun Int.formatDateString() = if (this >= 10) {
            "$this"
        } else {
            "0$this"
        }
    }

}