package com.example.popular.domain

import com.example.core_data.model.MovieModel
import javax.inject.Inject

class RemoveFromFavoritesUseCase @Inject constructor(
    private val repository: PopularMoviesRepository
) {
    suspend operator fun invoke(movieModel: MovieModel) {
        repository.removeFromFavorites(movieModel)
    }
}