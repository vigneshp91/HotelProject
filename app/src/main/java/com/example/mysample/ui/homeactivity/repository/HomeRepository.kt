package com.example.mysample.ui.homeactivity.repository

import android.util.Log
import com.example.mysample.localdatabase.dao.HotelCommentsDao
import com.example.mysample.localdatabase.dao.HotelDao
import com.example.mysample.ui.homeactivity.model.HotelModel
import com.example.mysample.network.ApiService
import com.example.mysample.network.NetworkCallback
import com.example.mysample.ui.homeactivity.model.HotelComments
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository(private val apiService: ApiService){
    fun getHotelData(callback: NetworkCallback) {



        apiService.getHotel()?.enqueue(object : Callback<HotelModel> {
            override fun onFailure(call: Call<HotelModel>, t: Throwable) {
                Log.e("data", "" + t.message)
                callback.onError(t)
            }

            override fun onResponse(call: Call<HotelModel>, response: Response<HotelModel>) {
                Log.e("data", "" + response.code())
                callback.onSuccess(response)

            }

        })
    }

    fun getHotelComments(callback: NetworkCallback) {

        apiService.getHotelComments()?.enqueue(object : Callback<ArrayList<HotelComments>> {
            override fun onFailure(call: Call<ArrayList<HotelComments>>, t: Throwable) {
                Log.e("data", "" + t.message)
                callback.onError(t)
            }

            override fun onResponse(call: Call<ArrayList<HotelComments>>, response: Response<ArrayList<HotelComments>>) {
                Log.e("data", "" + response.code())
                callback.onSuccess(response)

            }

        })
    }

}