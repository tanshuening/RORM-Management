package com.example.rorm_management

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.rorm_management.databinding.ActivityAddPromotionBinding

class AddPromotionActivity : AppCompatActivity() {

    private val binding: ActivityAddPromotionBinding by lazy {
        ActivityAddPromotionBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

    }
}