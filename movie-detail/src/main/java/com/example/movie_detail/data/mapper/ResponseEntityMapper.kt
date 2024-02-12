package com.example.movie_detail.data.mapper

import com.example.core_data.model.MovieModel
import com.example.movie_detail.data.network.dto.MovieDetailDto
import javax.inject.Inject

class ResponseEntityMapper @Inject constructor() {
    fun mapResponseToEntity(
        dto: MovieDetailDto
    ) = MovieModel(
        year = dto.year.toString(),
        id = dto.kinopoiskId.toString(),
        title = dto.nameRu,
        genre = dto.genres[0].genre,
        isFavorite = false,
        imagePoster = dto.posterUrl,
        description = dto.description,
        country = dto.countries[0].country
    )
}