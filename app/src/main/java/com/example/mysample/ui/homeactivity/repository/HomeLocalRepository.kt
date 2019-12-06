package com.example.mysample.ui.homeactivity.repository

import com.example.mysample.local.dao.HotelCommentsDao
import com.example.mysample.local.dao.HotelDao
import com.example.mysample.network.ApiService
import com.example.mysample.network.ConnectivityUtils
import com.example.mysample.network.NetworkCallback
import com.example.mysample.ui.homeactivity.model.HotelComments
import com.example.mysample.ui.homeactivity.model.HotelModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class HomeLocalRepository @Inject constructor(val hotelDao: HotelDao,
                                              val hotelCommentsDao: HotelCommentsDao
):BaseRepository() {

    fun getHotelDataFromSource(callback: NetworkCallback) {
            getHoteData(hotelDao.getHotelDetails(),callback)

    }
    fun getHotelCommentsDataFromSource(callback: NetworkCallback) {
            getHoteCommentsData(hotelCommentsDao.getHotelComments(),callback)

    }

    private fun getHoteCommentsData(
        hotelComments: Observable<*>,
        callback: NetworkCallback) {
        val disposable = CompositeDisposable()

        getData(hotelComments,object : DisposableObserver<Any>() {
            override fun onNext(response: Any) {
                callback.onSuccess(response)
                disposable.dispose()
            }

            override fun onError(throwable: Throwable) {
                callback.onError(throwable)
                disposable.dispose()
            }

            override fun onComplete() {
                disposable.dispose()
            }

        },disposable)    }


    private fun getHoteData(observable: Observable<*>, callback: NetworkCallback) {
        val disposable = CompositeDisposable()

        getData(observable,object : DisposableObserver<Any>() {
            override fun onNext(response: Any) {
                callback.onSuccess(response)
                disposable.dispose()
            }

            override fun onError(throwable: Throwable) {
                callback.onError(throwable)
                disposable.dispose()
            }

            override fun onComplete() {
                disposable.dispose()
            }

        },disposable)
    }

}