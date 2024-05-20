package com.skintone.me.ui.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.skintone.me.databinding.ActivityIntroSlider2Binding

class IntroSliderActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityIntroSlider2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroSlider2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNextLogin.setOnClickListener {
            startActivity(Intent(this, IntroSliderActivity3::class.java))
        }


    }
}