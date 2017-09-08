/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.mvp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import pl.orbitemobile.wspolnoty.R

class MVP {

    interface Presenter {
        fun onViewCreated()
        fun onDestroy()
        fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?)
        fun onStart()
        fun onResume()
        fun onPause()
        fun onStop()
        fun onRestart()
        fun onOptionsItemSelected(item: MenuItem): Boolean
    }

    interface MVPView {
        /**
         * In some classes, like Fragments there is method getContext, and defining it as contract
         * in MVPView ensures that presenter can have access to context via view.
         * This prevents presenter from necessity from storing this reference on its own.
         * */
        fun getContext(): Context
    }

    abstract class BasePresenter<ViewContract : MVPView> : Presenter {
        var mView: ViewContract? = null

        fun startActivity(activityClass: Class<*>, bundle: Bundle? = null) {
            val context = mView?.getContext() ?: return //todo: log
            val intent = Intent(context, activityClass)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }

        /**
         * All methods for life cycle handling are implemented in BasePresenter class
         * to ensure that specif presenter implementations can override only those methods
         * that are going to be used.
         * In specific presenter implementation it is unnecessary to implement every method
         * handling lifecycle. Therefore those methods are implemented below as plain, so presenters
         * can override ones that are needed for specific use cases.
         * */

        override fun onViewCreated() {}

        override fun onDestroy() {}

        override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {}

        override fun onStart() {}

        override fun onResume() {}

        override fun onPause() {}

        override fun onStop() {}

        override fun onRestart() {}
    }

    abstract class BaseView<PresenterContract : Presenter>(val layoutId: Int) : Fragment(), MVPView {
        var mPresenter: PresenterContract? = null

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) = inflater!!.inflate(layoutId, container, false)!!

        override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            (mPresenter as Presenter).onViewCreated()
        }

        protected fun View.setGone() {
            visibility = View.GONE
        }

        protected fun View.setVisible() {
            visibility = View.VISIBLE
        }

        protected fun setGone(vararg views: View) {
            views.forEach { it.setGone() }
        }

        protected fun setVisible(vararg views: View) {
            views.forEach { it.setVisible() }
        }
    }

    abstract class Activity : AppCompatActivity() {
        lateinit var mPresenter: Presenter

        override fun onDestroy() {
            super.onDestroy()
            mPresenter.onDestroy()
        }

        override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
            super.onCreate(savedInstanceState, persistentState)
            mPresenter.onCreate(savedInstanceState, persistentState)
        }

        override fun onStart() {
            super.onStart()
            mPresenter.onStart()
        }

        override fun onResume() {
            super.onResume()
            mPresenter.onResume()
        }

        override fun onPause() {
            mPresenter.onPause()
            super.onPause()
        }

        override fun onStop() {
            mPresenter.onStop()
            super.onStop()
        }

        override fun onRestart() {
            super.onRestart()
            mPresenter.onRestart()
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return mPresenter.onOptionsItemSelected(item)
        }

        @Suppress("UNCHECKED_CAST")
        inline fun <reified PresenterContract : Presenter,
                reified ViewContract : MVPView,
                reified PresenterContractImpl : BasePresenter<ViewContract>,
                reified ViewContractImpl : BaseView<PresenterContract>> init() {
            val view = getView<ViewContractImpl>(supportFragmentManager)
            val presenter = newInstance<PresenterContractImpl>()
            presenter.mView = castToContract<ViewContractImpl, ViewContract>(view)
            view.mPresenter = castToContract<PresenterContractImpl, PresenterContract>(presenter)
            setFragment(view)
            mPresenter = presenter
        }

        inline fun <reified ContractImpl, reified Contract> castToContract(impl: ContractImpl): Contract {
            if (impl !is Contract) {
                throw ClassCastException("Contract Implementation Class: ${className<ContractImpl>()} can not be casted to Contract Class: ${className<Contract>()} ")
            }
            return impl
        }

        inline fun <reified T> className() = T::class.java.simpleName!!

        fun setFragment(view: Fragment) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, view)
            transaction.commit()
        }

        inline fun <reified ViewContractImpl> getView(fragmentManager: FragmentManager): ViewContractImpl {
            return (fragmentManager.findFragmentById(R.id.fragment_container) ?: newInstance<ViewContractImpl>()) as ViewContractImpl
        }

        inline fun <reified T> newInstance() = T::class.java.newInstance()!!
    }
}