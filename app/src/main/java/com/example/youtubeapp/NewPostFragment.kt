package com.example.youtubeapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.youtubeapp.databinding.FragmentNewPostBinding


class NewPostFragment : Fragment() {

    private var _binding : FragmentNewPostBinding? = null
    private val binding get() = _binding!!

    //listener
    var listener : OnNewPostListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNewPostBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.publishBtn.setOnClickListener{
            val caption = binding.captionET.text.toString()
            val image = binding.postImage
            val city = binding.cityET.text.toString()
            //publish
            listener?.let {
                it.onNewPost(city,caption, image)

            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnNewPostListener{
        fun onNewPost(city:String,captionPpost: String, image:ImageView)
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewPostFragment()
    }
}