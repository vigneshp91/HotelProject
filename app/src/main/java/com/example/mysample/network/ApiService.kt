package com.example.mysample.network

import com.example.mysample.ui.homeactivity.model.HotelComments
import com.example.mysample.ui.homeactivity.model.HotelModel
import com.google.gson.JsonArray
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import java.util.*
import kotlin.collections.ArrayList


interface ApiService{
    /**
     * endpoint to get hotel details
     */
    @GET("/api/workshop/hotel")
    fun getHotel(): Observable<HotelModel>

    @GET("/api/workshop/comments")
    fun getHotelComments(): Observable<ArrayList<HotelComments>>
}