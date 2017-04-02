/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.hours

import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class HoursPresenter(override var disposable: CompositeDisposable? = CompositeDisposable(),
                     override var view: HoursContract.View? = null) : HoursContract.Presenter {

    private val useCase: HoursContract.UseCase = HoursUseCase()

    override fun onViewAttached() {
        view?.showLoadingScreen()
        useCase.getRemoteHours().subscribe(this)
    }

    override fun onRetryClick() {
        if (isNetwork()) {
            view?.showLoadingScreen()
            useCase.getRemoteHours().subscribe(this)
        } else {
            view?.showNetworkToast()
        }
    }

    override fun onError(e: Throwable) {
        view?.showErrorMessage()
        e.printStackTrace()
    }

    override fun onSuccess(@NonNull hours: String) {
        view?.showHours(hours)
    }


    override fun onSubscribe(d: Disposable) {
        disposable?.addAll(d)
    }
}
