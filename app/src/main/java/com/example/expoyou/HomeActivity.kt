package com.example.expoyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import com.example.expoyou.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.Channel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var currentFragment: Fragment


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val videoFragment = VideoFragment()
        val channelFragment = ChannelFragment()

        setCurrentFragment(videoFragment)
        binding.bottomNaviagtion.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.VideoMenu -> setCurrentFragment(videoFragment)
                R.id.ChannelMenu -> setCurrentFragment(channelFragment)
            }
            true
        }


    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_container, fragment)
            commit()
        }
    }
}

