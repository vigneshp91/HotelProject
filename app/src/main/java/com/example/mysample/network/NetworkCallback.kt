package com.example.mysample.network

import com.example.mysample.ui.homeactivity.model.HotelModel

interface NetworkCallback{
    fun onSuccess(data: Any)
    fun onError(t:Throwable)
}
