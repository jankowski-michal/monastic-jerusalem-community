package pl.orbitemobile.mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class MvpView<PresenterContract : MvpPresenter<*>>(val layoutId: Int) : Fragment() {
    var presenter: PresenterContract? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) = inflater!!.inflate(layoutId, container, false)!!.bindViews()

    abstract fun View.bindViews(): View

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.onViewAttached()
    }
}