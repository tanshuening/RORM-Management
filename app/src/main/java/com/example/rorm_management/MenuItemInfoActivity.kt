package com.example.rorm_management

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.rorm_management.databinding.ActivityMenuItemInfoBinding

class MenuItemInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuItemInfoBinding
    private var menuItemName: String? = null
    private var menuItemPrice: String? = null
    private var menuItemDescription: String? = null
    private var menuItemIngredients: String? = null
    private var menuItemImage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuItemInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener{
            onBackPressed()  // Handle back button click
        }

        // Retrieve data from intent
        menuItemName = intent.getStringExtra("MenuItemName")
        menuItemPrice = intent.getStringExtra("MenuItemPrice")
        menuItemDescription = intent.getStringExtra("MenuItemDescription")
        menuItemIngredients = intent.getStringExtra("MenuItemIngredients")
        menuItemImage = intent.getStringExtra("MenuItemImage")

        // Set data to views
        with(binding) {
            menuItemNameTextView.text = menuItemName
            menuItemPriceTextView.text = menuItemPrice
            menuItemDescriptionTextView.text = menuItemDescription
            menuItemIngredientsTextView.text = menuItemIngredients
            Glide.with(this@MenuItemInfoActivity).load(Uri.parse(menuItemImage))
                .into(menuItemImageView)
        }
    }
}
