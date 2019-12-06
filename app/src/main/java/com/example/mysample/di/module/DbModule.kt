package com.example.mysample.di.module

import android.app.Application
import androidx.room.Room
import com.example.mysample.local.AppDatabase
import com.example.mysample.local.dao.HotelCommentsDao
import com.example.mysample.local.dao.HotelDao
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    internal fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application, AppDatabase::class.java, "Hotel.db")
            .allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    internal fun provideHotelCommentsDao(appDatabase: AppDatabase): HotelCommentsDao {
        return appDatabase.hotelCommentsDao()
    }


    @Provides
    @Singleton
    internal fun provideHotelDao(appDatabase: AppDatabase): HotelDao {
        return appDatabase.hotelDao()
    }
}
