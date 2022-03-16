package com.example.youtubeapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissions(arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ),1)

        nextBtn.setOnClickListener {
            login()
        }


    }

    private fun login(){
        val userEmail = loginEmail.text.toString()
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra("userEmail", userEmail)
        }
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1){
            var allGrant = true;
            for(result in grantResults){
                if(result == PackageManager.PERMISSION_DENIED) allGrant = false
            }
            if(allGrant){

            }else{
                Toast.makeText(this, "Tiene que aceptar todos los permisos para poder continuar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}