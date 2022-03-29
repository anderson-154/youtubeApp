package com.example.youtubeapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.youtubeapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var users = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val user1 =  User("Anderson", "alfa@gmail.com","aplicacionesmoviles")
        val user2 =  User("Benjamin", "beta@gmail.com","aplicacionesmoviles")
        users.add(user1)
        users.add(user2)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

       /* requestPermissions(arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ),1)*/

        binding.nextBtn.setOnClickListener {
            login()
        }


    }

    private fun login(){
        val intent = Intent(this, HomeActivity::class.java)
        val email = binding.loginEmail.text.toString()
        val password = binding.loginPassword.text.toString()
        var current:User? = null
        var name = ""
        for (user in users){
            if(email.equals(user.email) and password.equals(user.password)) {
                current = user
                name = user.name
                break
            }
        }
        if(current !=null){
            startActivity(intent)
        }else{
            intent.putExtra("name",name)
            Toast.makeText(this.baseContext,"Datos incorrectos",Toast.LENGTH_LONG).show()
        }
    }

    /**override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1){
            var allGrant = true;
            for(result in grantResults){
                if(result == PackageManager.PERMISSION_DENIED) {
                    allGrant = false
                    break
                }
            }
            if(allGrant){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "Tiene que aceptar todos los permisos para poder continuar", Toast.LENGTH_SHORT).show()
            }
        }
    }*/
}