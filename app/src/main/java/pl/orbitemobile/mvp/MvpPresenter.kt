package pl.orbitemobile.mvp

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.view.MenuItem
import io.reactivex.disposables.CompositeDisposable

interface MvpPresenter<ViewContract> {
    var disposable: CompositeDisposable?
    var view: ViewContract?
    fun onViewAttached()
    fun onOptionsItemSelected(item: MenuItem): Boolean = true
    fun onStart(savedInstanceState: Bundle? = null, persistentState: PersistableBundle? = null): Unit {}
    fun onStop() {
        disposable?.clear()
    }

    fun isNetwork(): Boolean {
        val view = view ?: return false
        val connectivityManager = (view as Fragment).context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
