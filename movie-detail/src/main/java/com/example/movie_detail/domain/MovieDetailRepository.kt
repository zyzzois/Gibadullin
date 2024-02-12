package com.example.movie_detail.domain

import com.example.core_data.model.MovieModel

interface MovieDetailRepository {
    suspend fun getMovieById(id: String): MovieModel
}