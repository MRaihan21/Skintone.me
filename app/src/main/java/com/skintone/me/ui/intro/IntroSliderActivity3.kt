package com.skintone.me.ui.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.skintone.me.databinding.ActivityIntroSlider3Binding
import com.skintone.me.ui.LoginActivity

class IntroSliderActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivityIntroSlider3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroSlider3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNextIntro2.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }
}