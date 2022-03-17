package com.example.youtubeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.youtubeapp.databinding.FragmentNewPostBinding


class NewPostFragment : Fragment() {

    private lateinit var binding : FragmentNewPostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewPostBinding.inflate(inflater, container, false)
        val view = binding.root

        // Inflate the layout for this fragment

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewPostFragment()
    }
}