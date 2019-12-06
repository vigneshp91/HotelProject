package com.example.mysample.ui.homeactivity.model

import androidx.room.Entity

@Entity
class HotelModel {
    var name :String? = null
    var description :String? = null
    var location :String? = null
    var rating :Float? = null
    var numberOfReviews :String? = null
    var cost :Float? = null

}