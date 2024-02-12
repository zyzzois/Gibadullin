package com.example.core_data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    val id: String,
    val year: String,
    val title: String,
    val genre: String,
    val isFavorite: Boolean,
    val imagePoster: String,
    val description: String? = null,
    val country: String? = null,
): Parcelable
