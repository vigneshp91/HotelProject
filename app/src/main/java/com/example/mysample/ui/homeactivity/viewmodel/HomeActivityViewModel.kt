package com.example.mysample.ui.homeactivity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysample.localdatabase.LocalDataBase
import com.example.mysample.ui.homeactivity.model.HotelModel
import com.example.mysample.network.RetroClient
import com.example.mysample.ui.homeactivity.repository.HomeRepository
import com.example.mysample.network.NetworkCallback
import com.example.mysample.ui.homeactivity.model.HotelComments
import com.google.gson.JsonArray
import retrofit2.Response

/**
 * Viewmodel for the home page
 */
class HomeActivityViewModel : ViewModel() {
    var getHotelResponse : MutableLiveData<HotelModel> = MutableLiveData()
    var hotelComments : MutableLiveData<ArrayList<HotelComments>> = MutableLiveData()
    var apierror : MutableLiveData<Throwable> = MutableLiveData()
    val api = RetroClient.apiService

    var repository:HomeRepository = HomeRepository(api)
    fun getHotelData() {
        /**
         * access repository to get data
         */
        repository.getHotelData(object : NetworkCallback {
            override fun onSuccess(data: Response<*>) {
                getHotelResponse.value =data.body() as HotelModel
            }

            override fun onError(t: Throwable) {
                apierror.value = t
            }

        })

    }  fun getHotelCommentsData() {
        /**
         * access repository to get data
         */
        apierror.value = null
        repository.getHotelComments(object : NetworkCallback {
            override fun onSuccess(data: Response<*>) {
                hotelComments.value =data.body() as ArrayList<HotelComments>

            }
            override fun onError(t: Throwable) {
                apierror.value = t
            }

        })

    }

}
