package com.example.youtubeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapp.databinding.FragmentRecyclerPostViewBinding

class RecyclerPostFragment : Fragment(), NewPostFragment.OnNewPostListener {

    private var _binding : FragmentRecyclerPostViewBinding? = null
    private val binding  get() = _binding!!

    //state
    private val adapter = PostAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecyclerPostViewBinding.inflate(inflater, container, false)
        val view = binding.root

        //recrear estado
        val postRecycler = binding.postRecycler
        postRecycler.setHasFixedSize(true)
        postRecycler.layoutManager = LinearLayoutManager(activity)
        postRecycler.adapter = adapter

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() =  RecyclerPostFragment()
    }

    override fun onNewPost(city:String,captionPpost: String,image:ImageView, autor:String, date:String) {
        val newPost = Post(city,captionPpost,image, autor, date)
        adapter.addPost(newPost)
    }
}