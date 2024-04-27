package com.example.dieticianapp.presentation.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.dieticianapp.R
import com.example.dieticianapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wada811.viewbindingktx.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::bind)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v("MainActivity", "onCreate")
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navController) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavigation,navHostFragment.navController)
    }
    companion object {
        fun callIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}