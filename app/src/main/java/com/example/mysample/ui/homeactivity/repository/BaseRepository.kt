package com.example.mysample.ui.homeactivity.repository

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

open class BaseRepository {
    open fun getData(observable: Observable<*>, observer: DisposableObserver<Any>, disposable: CompositeDisposable){

        val disposableObserver = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(observer)

        disposable.add(disposableObserver)
    }
}