package com.example.youtubeapp

import android.widget.ImageView

class Post {
    var caption : String
    var city : String
    var imagePost : ImageView


    constructor(city:String, caption:String, imagePost:ImageView){
        this.city = city
        this.caption = caption
        this.imagePost = imagePost
    }


}