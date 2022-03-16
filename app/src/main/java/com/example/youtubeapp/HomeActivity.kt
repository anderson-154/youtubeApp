package com.example.youtubeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    //private lateinit var layoutMangaer : LinearLayoutManager
    private lateinit var navigator : BottomNavigationView

    private lateinit var newPostFrament : NewPostFragment
    private lateinit var profileFragemnt : ProfileFragment


    //private lateinit var adapter : PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //layoutMangaer = LinearLayoutManager(this)

       // postRecyclerView.layoutManager = layoutMangaer
        //postRecyclerView.setHasFixedSize(true)

        //adapter = PostAdapter()
        //postRecyclerView.adapter = adapter

        navigator = findViewById(R.id.navigator)


        newPostFrament = NewPostFragment.newInstance()
        profileFragemnt = ProfileFragment.newInstance()


        navigator.setOnItemSelectedListener { menuItem->
            if(menuItem.itemId == R.id.newMenu){
                showFragment(newPostFrament)
            }else if(menuItem.itemId == R.id.profileMenu){
                showFragment(profileFragemnt)
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