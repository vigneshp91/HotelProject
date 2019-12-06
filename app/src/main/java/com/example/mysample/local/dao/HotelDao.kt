package com.example.mysample.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mysample.ui.homeactivity.model.HotelModel
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface HotelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHotelDetails(data:HotelModel)

    @Query("SELECT * from HotelModel")
    fun getHotelDetails(): Observable<HotelModel>
}