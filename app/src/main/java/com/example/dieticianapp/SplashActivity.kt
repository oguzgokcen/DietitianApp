package com.example.dieticianapp

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.dieticianapp.data.local.DataStoreManager
import com.example.dieticianapp.databinding.ActivitySplashBinding
import com.wada811.viewbindingktx.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    val binding by viewBinding(ActivitySplashBinding::bind)
    @Inject
    lateinit var dataStoreManager: DataStoreManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private fun getUserId() {
        lifecycleScope.launch {
            dataStoreManager.accessToken.collect { accessToken ->
                accessToken?.let {
                    startActivity(MainActivity.callIntent(this@SplashActivity))
                } ?: run {
                    startActivity(AccountActivity.callIntent(this@SplashActivity))
                }
            }
        }
    }
}