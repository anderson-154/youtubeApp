package com.example.youtubeapp


/*class User {
    var name: String
    var email: String
    var password:String


    constructor(name:String,email:String,password:String){
        this.name = name
        this.email = email
        this.password = password
        var photo:String=""
    }
}*/
data class User(
    var name:String,
    var email:String,
    var password:String){

    var photo:String = ""
}