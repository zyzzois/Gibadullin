package com.example.favorites.domain

import com.example.core_data.model.MovieModel

interface FavoritesMoviesRepository {
    suspend fun getFavoritesMoviesList(): List<MovieModel>
}