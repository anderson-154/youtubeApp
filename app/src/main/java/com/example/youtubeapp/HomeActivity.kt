package com.example.youtubeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.youtubeapp.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    //private lateinit var layoutMangaer : LinearLayoutManager

    private lateinit var newPostFrament : NewPostFragment
    private lateinit var profileFragemnt : ProfileFragment
    private lateinit var recyclerFragemnt : RecyclerPostView
    private lateinit var binding: ActivityHomeBinding

    //private lateinit var adapter : PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //layoutMangaer = LinearLayoutManager(this)

       // postRecyclerView.layoutManager = layoutMangaer
        //postRecyclerView.setHasFixedSize(true)

        //adapter = PostAdapter()
        //postRecyclerView.adapter = adapter


        newPostFrament = NewPostFragment.newInstance()
        profileFragemnt = ProfileFragment.newInstance()
        recyclerFragemnt = RecyclerPostView.newInstance()

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