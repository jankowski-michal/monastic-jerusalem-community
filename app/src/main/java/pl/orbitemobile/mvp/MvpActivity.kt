package pl.orbitemobile.mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

abstract class MvpActivity(val activityLayoutId: Int) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityLayoutId)
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified PresenterContract : MvpPresenter<ViewContract>,
            reified ViewContract : MvpView<PresenterContract>,
            reified PresenterContractImpl : PresenterContract,
            reified ViewContractImpl : ViewContract> init(viewFrameContainerId: Int): Pair<PresenterContractImpl, ViewContractImpl> {
        val view: ViewContractImpl = getView(viewFrameContainerId)
        val presenter: PresenterContractImpl = newInstance()
        presenter.view = view
        view.presenter = presenter
        setFragment(viewFrameContainerId, view as Fragment)
        return presenter to view
    }

    fun setFragment(viewFrameContainerId: Int, view: Fragment) {
        if (view.isAdded) {
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