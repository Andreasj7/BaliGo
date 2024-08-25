package com.example.baligo

data class Recommendation(
    val name: String,
    val rating: Float,
    val googleMapsImageUrl: String, // URL untuk gambar Google Maps
    val review: String
)
