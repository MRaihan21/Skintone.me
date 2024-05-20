package com.skintone.me.ui.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.skintone.me.databinding.ActivityIntroSlider1Binding

class IntroSliderActivity1 : AppCompatActivity() {

    private lateinit var binding: ActivityIntroSlider1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroSlider1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNextIntro1.setOnClickListener {
            startActivity(Intent(this, IntroSliderActivity2::class.java))
        }

    }
}