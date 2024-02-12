package com.example.popular.data.mapper

import com.example.core_data.model.MovieModel
import com.example.popular.data.network.dto.Item
import javax.inject.Inject

class ResponseEntityMapper @Inject constructor() {

    fun mapDtoToEntity(dto: Item) = MovieModel(
        id = dto.kinopoiskId.toString(),
        year = dto.year,
        title = dto.nameRu,
        genre = dto.genres[0].genre,
        isFavorite = false,
        imagePoster = dto.posterUrlPreview
    )

}