package com.example.mysample.ui.homeactivity.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull


@Entity(
    tableName = "HotelComments"
)
class HotelComments {
    @PrimaryKey
    @NotNull
     var id: String = ""

     var comment: String? = null

     var user: String? = null



}