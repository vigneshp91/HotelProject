package com.example.mysample.di.module

import android.app.Application
import com.example.mysample.local.dao.HotelCommentsDao
import com.example.mysample.local.dao.HotelDao
import com.example.mysample.network.ApiService
import com.example.mysample.network.ConnectivityUtils
import com.example.mysample.ui.homeactivity.repository.HomeNetworkRepository
import com.example.mysample.ui.homeactivity.repository.HomeRepositoryInterface


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton
import java.io.File
import java.util.concurrent.TimeUnit


@Module
class ApiModule {

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        return httpClient.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://hiringworkshop.herokuapp.com")
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideMovieApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideHomeRepositoryInterface(apiService: ApiService,hotelDao: HotelDao,commentsDao: HotelCommentsDao,connectivityUtils: ConnectivityUtils): HomeRepositoryInterface {
        return HomeNetworkRepository(apiService,hotelDao,commentsDao,connectivityUtils)
    }

    @Provides
    @Singleton
    fun provideConnectivityUtils(application:Application): ConnectivityUtils {
        return ConnectivityUtils(application)
    }


}
