package com.example.rorm_management

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rorm_management.databinding.ActivityPromotionBinding

class PromotionActivity : AppCompatActivity() {

    private val binding: ActivityPromotionBinding by lazy {
        ActivityPromotionBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddPromotionActivity::class.java)
            startActivity(intent)
        }
    }
}