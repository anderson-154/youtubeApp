package com.example.youtubeapp

import android.widget.ImageView

class Post {
    var caption : String
    var city : String
    var imagePost : ImageView
    var autor : String
    var date : String


    constructor(city:String, caption:String, imagePost:ImageView, autor:String, date:String){
        this.city = city
        this.caption = caption
        this.imagePost = imagePost
        this.autor = autor
        this.date = date
    }


}