package com.example.mysample.ui.homeactivity.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "HotelModel"
)
class HotelModel {
    @PrimaryKey(autoGenerate = true)
    var id :Int? = null
    var name :String? = null
    var description :String? = null
    var location :String? = null
    var rating :Float? = null
    var numberOfReviews :String? = null
    var cost :Float? = null

}