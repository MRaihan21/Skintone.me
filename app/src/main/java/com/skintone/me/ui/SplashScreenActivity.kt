package com.skintone.me.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.skintone.me.databinding.ActivitySplashScreenBinding
import com.skintone.me.ui.intro.IntroSliderActivity1

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    private val SPLASH_TIME_OUT: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, IntroSliderActivity1::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)

    }
}