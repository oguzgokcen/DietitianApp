package com.example.dieticianapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.dieticianapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var nav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews();
        startActivity(AccountActivity.callIntent(this))
        nav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeNav -> replaceFragment(HomeFragment())
                R.id.chatNav -> replaceFragment(ChatFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

    private fun bindViews() {
        nav = findViewById(R.id.bottom_navigation)
    }

    companion object {
        fun callIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}