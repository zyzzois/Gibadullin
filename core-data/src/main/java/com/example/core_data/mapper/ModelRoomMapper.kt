package com.example.core_data.mapper

import com.example.core_data.database.MovieRoomEntity
import com.example.core_data.model.MovieModel
import javax.inject.Inject

class ModelRoomMapper @Inject constructor() {
    fun mapModelToEntity(movieModel: MovieModel) = MovieRoomEntity(
        id = MovieRoomEntity.DEFAULT_ID,
        kinopoiskId = movieModel.id,
        title = movieModel.title,
        genre = movieModel.genre,
        isFavorite = true,
        imagePoster = movieModel.imagePoster,
        year = movieModel.year
    )
}