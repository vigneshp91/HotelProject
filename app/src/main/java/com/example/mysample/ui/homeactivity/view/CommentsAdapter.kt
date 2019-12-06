package com.example.mysample.ui.homeactivity.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mysample.R
import com.example.mysample.ui.homeactivity.model.HotelComments


class CommentsAdapter(private val mData:ArrayList<HotelComments>):RecyclerView.Adapter<CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.comments_item, null)
        return CommentViewHolder(view)    }

    override fun getItemCount(): Int {
       return mData.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
       holder.user.text = mData[position].getUser()
       holder.comments.text = mData[position].getComment()
   }
}

class  CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val user = view.findViewById<TextView>(R.id.userName)
    val comments = view.findViewById<TextView>(R.id.comments)

}
