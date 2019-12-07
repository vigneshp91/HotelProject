package com.example.mysample.ui.homeactivity.repository

import com.example.mysample.network.NetworkCallback

interface HomeRepositoryInterface {
    fun getHotelDataFromSource(callback: NetworkCallback)
    fun getHotelCommentsDataFromSource(callback: NetworkCallback)
}