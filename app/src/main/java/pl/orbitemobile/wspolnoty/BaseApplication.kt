package pl.orbitemobile.wspolnoty

import android.app.Application
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.Tracker


class BaseApplication : Application() {

    private var mTracker: Tracker? = null

    override fun onCreate() {
        super.onCreate()
    }

    val defaultTracker: Tracker?
        @Synchronized get() {
            if (mTracker == null) {
                val analytics = GoogleAnalytics.getInstance(this)
                mTracker = analytics.newTracker(R.xml.global_tracker)
            }
            return mTracker
        }
}