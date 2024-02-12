package com.example.movie_detail.data.network

import com.example.movie_detail.data.network.dto.MovieDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailApiService {

    @GET("/api/v2.2/films/{movieId}")
    suspend fun getMovieById(
        @Path("movieId") movieId: String
    ): MovieDetailDto

}