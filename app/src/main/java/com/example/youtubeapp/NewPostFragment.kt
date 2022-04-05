package com.example.youtubeapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.content.FileProvider
import com.example.youtubeapp.databinding.FragmentNewPostBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class NewPostFragment(val logUser:User) : Fragment() {

    private var _binding : FragmentNewPostBinding? = null
    private val binding get() = _binding!!
    private var file:File? = null
    private var imageUser: String=""
    //listener
    var listener : OnNewPostListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNewPostBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.publishBtn.setOnClickListener{
            //publish
            listener?.let {
                val caption = binding.captionET.text.toString()
                val city = binding.spinnerCity.selectedItem.toString()
                val date = getCurrentDateTime().toString("yyyy/MM/dd")
                it.onNewPost(city,caption, imageUser, logUser, date )
                binding.captionET.text.clear()
            }
        }

        val cameraLauncher = registerForActivityResult(StartActivityForResult(),::onCameraResult)
        val galleryLauncher = registerForActivityResult(StartActivityForResult(),::onGalleryResult)


        binding.cameraBtn.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            file = File("${this.requireActivity().getExternalFilesDir(null)}/photo.png")
            Log.e(">>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<", file?.path.toString())
            val uri = FileProvider.getUriForFile(this.requireActivity(), requireActivity().packageName ,file!!)
            intent.putExtra(MediaStore.EXTRA_OUTPUT,uri)
            this.imageUser = uri.toString()
            cameraLauncher.launch(intent)
        }

        binding.galeryBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            galleryLauncher.launch(intent)
        }

        return view
    }

    fun onCameraResult(result: ActivityResult){
        if(result.resultCode == Activity.RESULT_OK){
            val bitmap = BitmapFactory.decodeFile(file?.path)
            val thumball = Bitmap.createScaledBitmap(bitmap, bitmap.width/4,bitmap.height/4,true)
            binding.postImage.setImageBitmap(thumball)
        }else if (result.resultCode == Activity.RESULT_CANCELED){
            Toast.makeText(this.activity,"No tomó foto", Toast.LENGTH_SHORT).show()
        }
    }

    fun onGalleryResult(result : ActivityResult){
        if(result.resultCode == Activity.RESULT_OK){
            val uriImage = result.data?.data
            uriImage?.let {
                binding.postImage.setImageURI(uriImage)
            }
        }else if (result.resultCode == Activity.RESULT_CANCELED){
            Toast.makeText(this.activity,"No se selecciono una imagen", Toast.LENGTH_SHORT).show()
        }
    }

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnNewPostListener{
        fun onNewPost(city:String,captionPost: String, image:String, autor:User, date:String)
    }

    companion object {
        @JvmStatic
        fun newInstance(logUser:User) = NewPostFragment(logUser)
    }
}