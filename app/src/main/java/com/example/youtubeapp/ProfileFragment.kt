package com.example.youtubeapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.youtubeapp.databinding.FragmentProfileBinding
import java.io.File
import androidx.activity.result.ActivityResult

class ProfileFragment(val logUser:User) : Fragment() {

    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var file:File? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        val cameraLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ::onCameraResult
        )
        val galleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ::onGalleryResult
        )

        binding.nameTV.text = logUser.name
        binding.nameTV2.text = logUser.name
        binding.emailTV.text = logUser.email

        binding.profileImage.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            file = File("${this.requireActivity().getExternalFilesDir(null)}/photo.png")
            val uri = FileProvider.getUriForFile(
                this.requireActivity(),
                requireActivity().packageName,
                file!!
            )
            logUser.photo = uri.toString()
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            cameraLauncher.launch(intent)
        }

        binding.galleryBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            galleryLauncher.launch(intent)
        }

        binding.outBtn.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    fun onCameraResult(result:ActivityResult){
        if(result.resultCode == Activity.RESULT_OK){
            val bitmap = BitmapFactory.decodeFile(file?.path)
            val thumball = Bitmap.createScaledBitmap(bitmap, bitmap.width/4,bitmap.height/4,true)
            binding.profileImage.setImageBitmap(thumball)
        }else if (result.resultCode == Activity.RESULT_CANCELED){
            Toast.makeText(this.activity,"No tomó foto", Toast.LENGTH_SHORT).show()
        }
    }

    fun onGalleryResult(result:ActivityResult){
        if(result.resultCode == Activity.RESULT_OK){
            val uriImage = result.data?.data
            logUser.photo = uriImage.toString()
            uriImage?.let {
                binding.profileImage.setImageURI(uriImage)
            }
        }else if (result.resultCode == Activity.RESULT_CANCELED){
            Toast.makeText(this.activity,"No se selecciono una imagen", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    companion object {
        @JvmStatic
        fun newInstance(logUser:User) =  ProfileFragment(logUser)
    }
}