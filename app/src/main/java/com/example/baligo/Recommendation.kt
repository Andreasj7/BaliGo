package com.example.baligo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recommendation(
    val imageResId: Int,
    val title: String,
    val rating: Float,
    val description: String
) : Parcelable
