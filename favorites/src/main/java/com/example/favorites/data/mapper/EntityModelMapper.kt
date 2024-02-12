package com.example.favorites.data.mapper

import com.example.core_data.database.MovieRoomEntity
import com.example.core_data.model.MovieModel
import javax.inject.Inject

class EntityModelMapper @Inject constructor() {
    fun mapModelToEntity(movieModel: MovieModel) = MovieRoomEntity(
        id = MovieRoomEntity.DEFAULT_ID,
        kinopoiskId = movieModel.id,
        title = movieModel.title,
        genre = movieModel.genre,
        isFavorite = true,
        imagePoster = movieModel.imagePoster,
        year = movieModel.year
    )

    fun mapEntityToModel(movieRoomEntity: MovieRoomEntity) = MovieModel(
        id = movieRoomEntity.kinopoiskId,
        title = movieRoomEntity.title,
        genre = movieRoomEntity.genre,
        isFavorite = movieRoomEntity.isFavorite,
        imagePoster = movieRoomEntity.imagePoster,
        year = movieRoomEntity.year
    )
}