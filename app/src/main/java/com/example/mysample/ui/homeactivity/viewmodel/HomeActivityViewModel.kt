package com.example.mysample.ui.homeactivity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysample.network.ConnectivityUtils
import com.example.mysample.ui.homeactivity.model.HotelModel
import com.example.mysample.ui.homeactivity.repository.HomeNetworkRepository
import com.example.mysample.network.NetworkCallback
import com.example.mysample.ui.homeactivity.model.HotelComments
import com.example.mysample.ui.homeactivity.repository.HomeLocalRepository
import com.example.mysample.ui.homeactivity.repository.IRepoProvider
import retrofit2.Response
import javax.inject.Inject

/**
 * Viewmodel for the home page
 */
class HomeActivityViewModel@Inject constructor(
                            val repository: HomeNetworkRepository,
                            val localrepository: HomeLocalRepository
                            ,val connectivityUtils: ConnectivityUtils
) : ViewModel(),IRepoProvider {
    var getHotelResponse : MutableLiveData<HotelModel> = MutableLiveData()
    var hotelComments : MutableLiveData<ArrayList<HotelComments>> = MutableLiveData()
    var apierror : MutableLiveData<Throwable> = MutableLiveData()

    fun getHotelData() {
        /**
         * access repository to get data
         */
        if (connectivityUtils.isConnectedToInternet()) {
            this.inRemoteRepository()
        } else
            this.inLocalRepository()


    }

    fun getHotelCommentsData() {
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


    override fun inRemoteRepository() {
        repository.getHotelDataFromSource(object : NetworkCallback {
            override fun onSuccess(data: Any) {
                getHotelResponse.value =data as HotelModel
            }

            override fun onError(t: Throwable) {
                apierror.value = t
            }

        })
    }

    override fun inLocalRepository() {
        localrepository.getHotelDataFromSource(object : NetworkCallback {
            override fun onSuccess(data: Any) {
                getHotelResponse.value =data as HotelModel
            }

            override fun onError(t: Throwable) {
                apierror.value = t
            }

        })
    }

}
