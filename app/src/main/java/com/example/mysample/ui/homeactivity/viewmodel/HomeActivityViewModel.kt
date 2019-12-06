package com.example.mysample.ui.homeactivity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysample.ui.homeactivity.model.HotelModel
import com.example.mysample.ui.homeactivity.repository.HomeRepository
import com.example.mysample.network.NetworkCallback
import com.example.mysample.ui.homeactivity.model.HotelComments
import retrofit2.Response
import javax.inject.Inject

/**
 * Viewmodel for the home page
 */
class HomeActivityViewModel@Inject constructor(
                            val repository: HomeRepository) : ViewModel() {
    var getHotelResponse : MutableLiveData<HotelModel> = MutableLiveData()
    var hotelComments : MutableLiveData<ArrayList<HotelComments>> = MutableLiveData()
    var apierror : MutableLiveData<Throwable> = MutableLiveData()

    fun getHotelData() {
        /**
         * access repository to get data
         */
        repository.getHotelDataFromSource(object : NetworkCallback {
            override fun onSuccess(data: Any) {
                getHotelResponse.value =data as HotelModel
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
        repository.getHotelCommentsDataFromSource(object : NetworkCallback {
            override fun onSuccess(data: Any) {
                hotelComments.value =data as ArrayList<HotelComments>

            }
            override fun onError(t: Throwable) {
                apierror.value = t
            }

        })

    }

}
