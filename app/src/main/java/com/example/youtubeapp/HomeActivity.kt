package com.example.youtubeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var layoutMangaer : LinearLayoutManager

    private lateinit var adapter : PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        layoutMangaer = LinearLayoutManager(this)

        postRecyclerView.layoutManager = layoutMangaer
        postRecyclerView.setHasFixedSize(true)

        adapter = PostAdapter()
        postRecyclerView.adapter = adapter
    }
}