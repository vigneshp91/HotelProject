package com.example.mysample.ui.homeactivity.repository

import com.example.mysample.local.dao.HotelCommentsDao
import com.example.mysample.local.dao.HotelDao

import com.example.mysample.ui.homeactivity.model.HotelModel
import com.example.mysample.network.ApiService
import com.example.mysample.network.ConnectivityUtils
import com.example.mysample.network.NetworkCallback
import com.example.mysample.ui.homeactivity.model.HotelComments
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiService: ApiService,
                                         val hotelDao: HotelDao,
                                         val hotelCommentsDao: HotelCommentsDao,
                                         val connectivityUtils: ConnectivityUtils):BaseRepository(){


    fun getHotelDataFromSource(callback: NetworkCallback) {
        if (connectivityUtils.isConnectedToInternet()){
            getHoteData(apiService.getHotel(),callback,true)
        }
        else
            getHoteData(hotelDao.getHotelDetails(),callback,false)

    }
    fun getHotelCommentsDataFromSource(callback: NetworkCallback) {
        if (connectivityUtils.isConnectedToInternet()){
            getHoteCommentsData(apiService.getHotelComments(),callback,true)
        }
        else
            getHoteCommentsData(hotelCommentsDao.getHotelComments(),callback,false)

    }

    private fun getHoteCommentsData(
        hotelComments: Observable<*>,
        callback: NetworkCallback,
        isConnected: Boolean
    ) {
        val disposable = CompositeDisposable()

        getData(hotelComments,object : DisposableObserver<Any>() {
            override fun onNext(response: Any) {
                if (isConnected && response is ArrayList<*>)
                    hotelCommentsDao.saveHotelComments(response as ArrayList<HotelComments>)
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


    private fun getHoteData(observable:Observable<*>,callback: NetworkCallback,isConnected:Boolean) {
        val disposable = CompositeDisposable()

        getData(observable,object : DisposableObserver<Any>() {
                override fun onNext(response: Any) {
                    if (isConnected)
                        hotelDao.insertHotelDetails(response as HotelModel)
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