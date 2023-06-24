package com.example.fooddeliveryapp.Entity

data class Review(
    var nomClient: String,
    val idRestaurant: Int,
    val idClient: Int,
    val commentaire: String,
    val note: Double
)
