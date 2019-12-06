package com.example.mysample.network

import retrofit2.Response

interface NetworkCallback{
    fun onSuccess(data:Response<*>)
    fun onError(t:Throwable)
}
