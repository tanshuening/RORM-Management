package com.example.rorm_management.model

data class User(
    val ownerName: String = "",
    val restaurantName: String = "",
    val email: String = "",
    val password: String = ""
)
