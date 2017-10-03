/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.mvp

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class MVP {
    interface Presenter {
        fun onViewAttached()
        fun onOptionsItemSelected(item: MenuItem): Boolean
        fun onStart(savedInstanceState: Bundle? = null, persistentState: PersistableBundle? = null)
        fun onStop()
    }

    interface MVPView {
        fun getContext(): Context
        fun isAdded(): Boolean
    }

    abstract class BasePresenter<ViewContract : MVPView> : Presenter {
        var mView: ViewContract? = null
        protected var mDisposable = CompositeDisposable()

        val ViewContract.ifAttached: ViewContract?
            get() {
                return if (this.isAdded()) {
                    this
                } else {
                    null
                }
            }


        override fun onStop() {
            mDisposable.clear()
        }


        fun onSubscribe(d: Disposable) {
            mDisposable.add(d)
        }


    }

    abstract class BaseView<PresenterContract : Presenter>(val layoutId: Int) : Fragment(), MVPView {
        var mPresenter: PresenterContract? = null

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) = inflater!!.inflate(layoutId, container, false)!!.bindViews()

        abstract fun View.bindViews(): View

        override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            (mPresenter as Presenter).onViewAttached()
        }


        inline fun <reified T : View> android.app.Activity.bind(id: Int) = findViewById(id) as T
    }

    abstract class Activity(val activityLayoutId: Int) : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(activityLayoutId)
        }

        @Suppress("UNCHECKED_CAST")
        inline fun <reified PresenterContract : MVP.Presenter,
                reified ViewContract : MVP.MVPView,
                reified PresenterContractImpl : MVP.BasePresenter<ViewContract>,
                reified ViewContractImpl : MVP.BaseView<PresenterContract>> init(viewFrameContainerId: Int): Pair<PresenterContractImpl, ViewContractImpl> {
            val view: ViewContractImpl = getView(viewFrameContainerId)
            val presenter: PresenterContractImpl = newInstance()
            presenter.mView = view.cast()
            view.mPresenter = presenter.cast()
            setFragment(viewFrameContainerId, view)
            return presenter to view
        }

        inline fun <reified ContractImpl, reified Contract> ContractImpl.cast(): Contract {
            if (this !is Contract) {
                throw ClassCastException("Contract Implementation Class: ${className<ContractImpl>()} can not be casted to Contract Class: ${className<Contract>()} ")
            }
            return this
        }

        inline fun <reified T> className() = T::class.java.simpleName!!

        fun setFragment(viewFrameContainerId: Int, view: Fragment) {
            if(view.isAdded){
                return
            }
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(viewFrameContainerId, view)
            transaction.commit()
        }

        inline fun <reified ViewContractImpl> getView(viewFrameContainerId: Int) =
                (supportFragmentManager.findFragmentById(viewFrameContainerId) ?: newInstance<ViewContractImpl>()) as ViewContractImpl

        inline fun <reified T> newInstance() = T::class.java.newInstance()!!
    }
}


infix inline fun <reified T> View.bind(id: Int) = findViewById(id) as T
