package com.example.dietitianapp.presentation.Detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dietitianapp.R
import com.example.dietitianapp.databinding.ActivityDetailBinding
import com.wada811.viewbindingktx.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityDetailBinding::bind)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        with(binding){
            ivBack.setOnClickListener {
                finish()
            }
        }

    }

    companion object {
        fun callIntent(context: Context) = Intent(context, DetailActivity::class.java)
    }
}