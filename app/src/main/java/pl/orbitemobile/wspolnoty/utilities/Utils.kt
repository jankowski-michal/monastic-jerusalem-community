package pl.orbitemobile.wspolnoty.utilities

import android.content.Context
import android.net.ConnectivityManager
import pl.orbitemobile.mvp.MVP
import java.util.*

fun getTodayDayMonthYear(): String {
    val calendar = Calendar.getInstance()
    val day = calendar.get(Calendar.DAY_OF_WEEK)
    val month = calendar.get(Calendar.MONTH)
    val year = calendar.get(Calendar.YEAR)
    return "$day$month$year"
}


fun MVP.BasePresenter<*>.isNetwork(): Boolean {
    val view = mView ?: return false
    val connectivityManager = view.getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}