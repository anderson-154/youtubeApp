package com.example.youtubeapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.youtubeapp.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var users = ArrayList<User>()
    var allGrant = true

    override fun onCreate(savedInstanceState: Bundle?) {
        val user1 = User("Anderson", "alfa@gmail.com", "aplicacionesmoviles")
        val user2 = User("Benjamin", "beta@gmail.com", "aplicacionesmoviles")
        users.add(user1)
        users.add(user2)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

         requestPermissions(arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ),1)

        binding.nextBtn.setOnClickListener {
            login()
        }


    }

    private fun login() {
        val intent = Intent(this, HomeActivity::class.java)
        val email = binding.loginEmail.text.toString()
        val password = binding.loginPassword.text.toString()
        var current: User? = null
        for (user in users) {
            if (user.email == email && user.password == password) {
                current = user
                break
            }
        }
        if (current != null && allGrant) {
            intent.putExtra("logUser", Gson().toJson(current))
            startActivity(intent)
        } else {
            Toast.makeText(this.baseContext, "Los datos ingresados no coinciden", Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            allGrant = true;
            for (result in grantResults) {
                if (result == PackageManager.PERMISSION_DENIED) {
                    allGrant = false
                }
            }
            }else {
                Toast.makeText(
                    this,
                    "Tiene que aceptar todos los permisos para poder continuar",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
}