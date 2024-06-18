package com.example.rorm_management

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.rorm_management.databinding.ActivityAddMenuItemBinding
import com.example.rorm_management.model.Menu
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class AddMenuItemActivity : AppCompatActivity() {

    private lateinit var menuItemName: String
    private lateinit var menuItemPrice: String
    private lateinit var menuItemDescription: String
    private lateinit var menuItemIngredients: String
    private var menuItemUri: Uri? = null

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    private val binding: ActivityAddMenuItemBinding by lazy {
        ActivityAddMenuItemBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        binding.addButton.setOnClickListener {
            menuItemName = binding.foodName.text.toString().trim()
            menuItemPrice = binding.foodPrice.text.toString().trim()
            menuItemDescription = binding.foodDescription.text.toString().trim()
            menuItemIngredients = binding.foodIngredients.text.toString().trim()

            if (!(menuItemName.isBlank() || menuItemPrice.isBlank() || menuItemDescription.isBlank() || menuItemIngredients.isBlank())) {
                uploadData()
                Toast.makeText(this, "Item uploaded successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.selectImage.setOnClickListener {
            pickImage.launch("image/*")
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun uploadData() {
        val menuRef = database.getReference("menu")
        val newItemKey = menuRef.push().key

        if (menuItemUri != null) {
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef = storageRef.child("menu_images/${newItemKey}.jpg")
            val uploadTask = imageRef.putFile(menuItemUri!!)

            uploadTask.addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                    val newItem = Menu(
                        menuItemName = menuItemName,
                        menuItemPrice = menuItemPrice,
                        menuItemDescription = menuItemDescription,
                        menuItemIngredients = menuItemIngredients,
                        menuItemImage = downloadUrl.toString()
                    )

                    newItemKey?.let { key ->
                        menuRef.child(key).setValue(newItem).addOnSuccessListener {
                            Toast.makeText(this, "Data uploaded successfully", Toast.LENGTH_SHORT).show()
                        } .addOnFailureListener {
                            Toast.makeText(this, "Data upload failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } .addOnFailureListener{
                Toast.makeText(this, "Image upload failed", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show()
        }
    }

    private val pickImage =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                binding.selectedImage.setImageURI(uri)
                menuItemUri = uri
            }
        }
}
