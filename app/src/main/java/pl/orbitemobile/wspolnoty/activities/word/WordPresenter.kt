/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.word

import android.os.Bundle
import android.os.PersistableBundle
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import pl.orbitemobile.wspolnoty.activities.word.domain.WordUseCase
import pl.orbitemobile.wspolnoty.data.dto.ReadingDTO

class WordPresenter(override var disposable: CompositeDisposable? = CompositeDisposable(),
                    override var view: WordContract.View? = null) : WordContract.Presenter {

    var READINGS = "READINGS"
    var readings: List<ReadingDTO>? = null
    var useCase: WordContract.UseCase = WordUseCase()

    override fun onRetryClick() {
        if (isNetwork()) {
            downloadReading()
        } else {
            view?.showNetworkToast()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        val readingsTmp = readings
        if (readingsTmp != null) {
            for ((i, reading) in readingsTmp.withIndex()) {
                outState?.putSerializable(READINGS + i, reading)
            }
        }
    }

    override fun onViewAttached() {
        val readingsTmp = readings
        if (readingsTmp == null) {
            downloadReading()
        } else {
            view?.showReadings(readingsTmp)
        }
    }

    private fun loadFromBundle(saved: Bundle?) {
        if (saved == null || !saved.containsKey(READINGS + 0)) {
            return
        }
        val readingsTmp: MutableList<ReadingDTO> = mutableListOf()
        var i = 0
        while (saved.containsKey(READINGS + i)) {
            readingsTmp.add(saved.getSerializable(READINGS + i) as ReadingDTO)
            i++
        }
        readings = readingsTmp
    }


    override fun onStart(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        loadFromBundle(savedInstanceState)
        if (readings == null || readings?.isEmpty() == true) {
            downloadReading()
        }
    }

    private fun downloadReading() {
        view?.showLoadingScreen()
        useCase.getTodayReading().subscribe(this)
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        view?.showErrorMessage()
    }

    override fun onSuccess(t: List<ReadingDTO>) {
        readings = t
        view?.showReadings(t)
    }

    override fun onSubscribe(d: Disposable) {
        disposable?.add(d)
    }
}