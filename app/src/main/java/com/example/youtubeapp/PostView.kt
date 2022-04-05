package com.example.youtubeapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostView(itemView : View) : RecyclerView.ViewHolder(itemView) {

    var caption : TextView = itemView.findViewById(R.id.captionRow)
    var photo : ImageView = itemView.findViewById(R.id.imagePost)
    var city : TextView = itemView.findViewById(R.id.cityRow)
    var autor : TextView = itemView.findViewById(R.id.userNameRow)
    var date : TextView = itemView.findViewById(R.id.dateRow)
    var profileImage : ImageView = itemView.findViewById(R.id.profileImageRow)
}