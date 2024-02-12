package com.example.core_data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val kinopoiskId: String,
    val title: String,
    val year: String,
    val genre: String,
    val isFavorite: Boolean,
    val imagePoster: String
) {
    companion object{
        const val DEFAULT_ID = 0
    }
}