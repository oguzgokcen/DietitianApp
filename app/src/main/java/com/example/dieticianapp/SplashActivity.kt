package com.example.dieticianapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.dieticianapp.data.local.DataStoreManager
import com.example.dieticianapp.presentation.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var dataStoreManager: DataStoreManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        CoroutineScope(Dispatchers.Main).launch {

            getUserId()
        }
    }
    private fun getUserId() {
        lifecycleScope.launch {
            dataStoreManager.accessToken.collect { accessToken ->
                accessToken?.let {
                    startActivity(MainActivity.callIntent(this@SplashActivity))
                } ?: run {
                    startActivity(AccountActivity.callIntent(this@SplashActivity))  //change it
                }
            }
        }
    }
}



