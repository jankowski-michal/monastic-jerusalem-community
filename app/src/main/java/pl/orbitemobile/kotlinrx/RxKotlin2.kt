/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.kotlinrx

import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException


fun <T> single(block: () -> T): Single<T> = Single.create<T> { it.onSuccess(block()) }

fun <T> Single<T>.fromIoToMainThread(): Single<T> = subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())