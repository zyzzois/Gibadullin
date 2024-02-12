package com.example.popular.domain

import com.example.core_data.model.MovieModel
import javax.inject.Inject

class AddMovieToFavoritesUseCase @Inject constructor(
    private val repository: PopularMoviesRepository
) {
    suspend operator fun invoke(movieModel: MovieModel) {
        repository.addMovieToFavorites(movieModel)
    }
}