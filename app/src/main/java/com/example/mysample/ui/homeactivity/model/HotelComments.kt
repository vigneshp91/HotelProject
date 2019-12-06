package com.example.mysample.ui.homeactivity.model

import androidx.room.Entity
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName



@Entity
class HotelComments {
    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("comment")
    @Expose
    private var comment: String? = null
    @SerializedName("user")
    @Expose
    private var user: String? = null
    @SerializedName("replies")
    @Expose
    private var replies: List<Any?>? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getComment(): String? {
        return comment
    }

    fun setComment(comment: String?) {
        this.comment = comment
    }

    fun getUser(): String? {
        return user
    }

    fun setUser(user: String?) {
        this.user = user
    }



    fun getReplies(): List<Any?>? {
        return replies
    }

    fun setReplies(replies: List<Any?>?) {
        this.replies = replies
    }
}