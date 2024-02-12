package com.example.popular.domain

import com.example.core_data.model.MovieModel

interface PopularMoviesRepository {
    suspend fun getMoviesList(): List<MovieModel>

    suspend fun addMovieToFavorites(movie: MovieModel)

    suspend fun removeFromFavorites(movie: MovieModel)
}