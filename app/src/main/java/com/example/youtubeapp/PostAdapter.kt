package com.example.youtubeapp

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PostAdapter : RecyclerView.Adapter<PostView>() {

   private val posts = ArrayList<Post>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostView {
        var inflater = LayoutInflater.from(parent.context)
        val rowView = inflater.inflate(R.layout.postsrow, parent, false)
        val postView = PostView(rowView)
        return postView
    }

    override fun onBindViewHolder(skeleton: PostView, position: Int) {
        val post = posts[position]
        if(post.autor.photo!="" && post.imagePost!=""){
            val uri1 = Uri.parse(post.autor.photo)
            skeleton.profileImage.setImageURI(uri1)
            val uri2 = Uri.parse(post.imagePost)
            skeleton.photo.setImageURI(uri2)
        }
        skeleton.caption.text = post.caption
        skeleton.autor.text = post.autor.name
        skeleton.city.text = post.city
        skeleton.date.text = post.date
    }

    fun addPost(post:Post){
        posts.add(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }


}