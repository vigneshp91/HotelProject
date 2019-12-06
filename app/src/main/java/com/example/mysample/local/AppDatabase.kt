package com.example.mysample.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mysample.local.dao.HotelCommentsDao
import com.example.mysample.local.dao.HotelDao
import com.example.mysample.ui.homeactivity.model.HotelComments
import com.example.mysample.ui.homeactivity.model.HotelModel

@Database(entities = [HotelModel::class,HotelComments::class], version = 2, exportSchema = false)

abstract class AppDatabase : RoomDatabase() {

    abstract fun hotelDao(): HotelDao

    abstract fun hotelCommentsDao(): HotelCommentsDao
}
