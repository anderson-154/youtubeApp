package com.example.youtubeapp

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostView(itemView : View) : RecyclerView.ViewHolder(itemView) {

    var caption : TextView = itemView.findViewById(R.id.captionET)
    var photo : ImageView = itemView.findViewById(R.id.imageView)
    var city : TextView = itemView.findViewById(R.id.cityTV)
}