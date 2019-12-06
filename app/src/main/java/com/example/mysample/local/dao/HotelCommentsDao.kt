package com.example.mysample.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mysample.ui.homeactivity.model.HotelComments
import io.reactivex.Observable

@Dao
interface HotelCommentsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveHotelComments(comments:ArrayList<HotelComments>)

    @Query("SELECT * from HotelComments")
    fun getHotelComments(): Observable<List<HotelComments>>
}