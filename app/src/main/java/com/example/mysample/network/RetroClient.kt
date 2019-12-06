package com.example.mysample.network

import com.example.mysample.MyApplication
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroClient {
    /********
     * URLS
     */
    private const val ROOT_URL = "https://hiringworkshop.herokuapp.com"

    /**
     * Get Retrofit Instance
     */
    @get:Synchronized
    private val retrofitInstance: Retrofit
         get() = Retrofit.Builder()
            .baseUrl(ROOT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    /**
     * Get API Service
     *
     * @return API Service
     */
    @get:Synchronized
    val apiService: ApiService
        get() = retrofitInstance.create(ApiService::class.java)


}