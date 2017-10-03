/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.word

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import pl.orbitemobile.wspolnoty.activities.word.domain.WordUseCase
import pl.orbitemobile.wspolnoty.data.dto.ReadingDTO

class WordPresenter : WordContract.Presenter() {
    var READINGS = "READINGS"
    var readings: List<ReadingDTO>? = null
    var useCase: WordContract.UseCase = WordUseCase()

    override fun onRetryClick() {
        downloadReading()
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
            mView?.showReadings(readingsTmp)
        }
    }

    fun loadFromBundle(saved: Bundle?) {
        val readingsTmp: MutableList<ReadingDTO> = mutableListOf()
        if (saved == null || saved.containsKey(READINGS + 0) == false) {
            return
        }
        var i = 0
        while (saved.containsKey(READINGS + i)) {
            readingsTmp.add(saved.getSerializable(READINGS + i) as ReadingDTO)
            i++
        }
        readings = readingsTmp
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = true

    override fun onStart(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        loadFromBundle(savedInstanceState)
        if (readings == null || readings?.isEmpty() == true) {
            downloadReading()
        }
    }

    private fun downloadReading() {
        mView?.ifAttached?.showLoadingScreen()
        useCase.getTodayReading().subscribe(this)
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        mView?.ifAttached?.showErrorMessage()
    }

    override fun onSuccess(t: List<ReadingDTO>) {
        readings = t
        mView?.ifAttached?.showReadings(t)
    }


    override fun onStop() {}
}