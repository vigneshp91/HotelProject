package com.example.mysample.ui.homeactivity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysample.network.ConnectivityUtils
import com.example.mysample.ui.homeactivity.model.HotelModel
import com.example.mysample.ui.homeactivity.repository.HomeNetworkRepository
import com.example.mysample.network.NetworkCallback
import com.example.mysample.ui.homeactivity.model.HotelComments
import com.example.mysample.ui.homeactivity.repository.HomeRepositoryInterface
import retrofit2.Response
import javax.inject.Inject

/**
 * Viewmodel for the home page
 */
class HomeActivityViewModel@Inject constructor(
    val repository: HomeRepositoryInterface
) : ViewModel() {
    var selectedTab: Int = -1
    var getHotelResponse : MutableLiveData<HotelModel> = MutableLiveData()
    var hotelComments : MutableLiveData<ArrayList<HotelComments>> = MutableLiveData()
    var apierror : MutableLiveData<Throwable> = MutableLiveData()
    var commentUpdated:Boolean = false
    init {
        hotelComments.value = ArrayList()
    }
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



    }

    fun updateComment(comment:HotelComments){
        commentUpdated =true
        comment.let { hotelComments.value?.add(it) }
    }

    fun getHotelCommentsData() {
        /**
         * access repository to get data
         */
        apierror.value = null
        repository.getHotelCommentsDataFromSource(object : NetworkCallback {
            override fun onSuccess(data: Any) {
                var dataList = ArrayList<HotelComments>()
                dataList = data as ArrayList<HotelComments>
                hotelComments.value?.addAll(dataList)

            }
            override fun onError(t: Throwable) {
                apierror.value = t
            }

        })

    }

    fun clearComments(){
        hotelComments.value?.clear()
    }

}
