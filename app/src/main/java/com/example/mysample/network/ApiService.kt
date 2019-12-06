package com.example.mysample.network

import com.example.mysample.ui.homeactivity.model.HotelComments
import com.example.mysample.ui.homeactivity.model.HotelModel
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET


interface ApiService{
    /**
     * endpoint to get hotel details
     */
    @GET("/api/workshop/hotel")
    fun getHotel(): Call<HotelModel>?

    @GET("/api/workshop/comments")
    fun getHotelComments(): Call<ArrayList<HotelComments>>?
}