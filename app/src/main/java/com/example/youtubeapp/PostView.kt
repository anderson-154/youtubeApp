package com.example.youtubeapp

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostView(itemView : View) : RecyclerView.ViewHolder(itemView) {

    var caption : TextView = itemView.findViewById(R.id.captionET)
    var publish : Button = itemView.findViewById(R.id.publishBtn)
    var galery : Button  = itemView.findViewById(R.id.galeryBtn)
    var camera : Button = itemView.findViewById(R.id.cameraBtn)
    var photo : ImageView = itemView.findViewById(R.id.imageView)


}