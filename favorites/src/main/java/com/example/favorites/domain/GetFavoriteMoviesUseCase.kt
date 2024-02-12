package com.example.favorites.domain

import com.example.core_data.model.MovieModel
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(
    private val repository: FavoritesMoviesRepository
) {
    suspend operator fun invoke(): List<MovieModel> {
        return repository.getFavoritesMoviesList()
    }
}