package com.example.youtubeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.youtubeapp.databinding.ActivityHomeBinding
import com.google.gson.Gson


class HomeActivity : AppCompatActivity() {

    private lateinit var newPostFrament : NewPostFragment
    private lateinit var profileFragemnt : ProfileFragment
    private lateinit var recyclerFragemnt : RecyclerPostFragment

    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        val logUser = intent.getStringExtra("logUser")
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var user = Gson().fromJson(logUser, User::class.java)

        newPostFrament = NewPostFragment.newInstance(user)
        profileFragemnt = ProfileFragment.newInstance(user)
        recyclerFragemnt = RecyclerPostFragment.newInstance()

        //suscription
        newPostFrament.listener = recyclerFragemnt

        showFragment(recyclerFragemnt)

        binding.navigator.setOnItemSelectedListener { menuItem->
            if(menuItem.itemId == R.id.newMenu){
                showFragment(newPostFrament)
            }else if(menuItem.itemId == R.id.profileMenu){
                showFragment(profileFragemnt)
            }else if(menuItem.itemId == R.id.homeMenu){
                showFragment(recyclerFragemnt)
            }
            true
        }

    }

    fun showFragment(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.homeFragment, fragment)
        transaction.commit()
    }

}