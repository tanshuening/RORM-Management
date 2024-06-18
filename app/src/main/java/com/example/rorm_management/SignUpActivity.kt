package com.example.rorm_management

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rorm_management.databinding.ActivitySignUpBinding
import com.example.rorm_management.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var email: String
    private lateinit var password: String
    private lateinit var ownerName: String
    private lateinit var restaurantName: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        binding.signUpButton.setOnClickListener {
            ownerName = binding.ownerName.text.toString().trim()
            restaurantName = binding.restaurantName.text.toString().trim()
            email = binding.email.text.toString().trim()
            password = binding.password.text.toString().trim()

            if (ownerName.isBlank() || restaurantName.isBlank() || email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show()
            } else {
                createAccount(email, password)
            }
        }

        binding.login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show()
                saveUserData()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
                Log.d("Account", "CreateAccount: Failure", task.exception)
            }
        }
    }

    private fun saveUserData() {
        ownerName = binding.ownerName.text.toString().trim()
        restaurantName = binding.restaurantName.text.toString().trim()
        email = binding.email.text.toString().trim()
        password = binding.password.text.toString().trim()

        val user = User(ownerName, restaurantName, email, password)
        val userId: String = FirebaseAuth.getInstance().currentUser!!.uid
        database.child("users").child(userId).setValue(user)
    }
}