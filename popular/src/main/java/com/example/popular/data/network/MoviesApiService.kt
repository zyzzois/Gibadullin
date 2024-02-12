package com.example.popular.data.network

import com.example.popular.data.network.dto.MovieDto
import retrofit2.http.GET

interface MoviesApiService {
    @GET("/api/v2.2/films/collections?type=TOP_250_MOVIES")
    suspend fun getTop100PopularMovies(): MovieDto
}