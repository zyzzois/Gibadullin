package com.example.movie_detail.data.repository

import android.util.Log
import com.example.core_data.model.MovieModel
import com.example.movie_detail.data.mapper.ResponseEntityMapper
import com.example.movie_detail.data.network.MovieDetailApiService
import com.example.movie_detail.domain.MovieDetailRepository
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val apiService: MovieDetailApiService,
    private val mapper: ResponseEntityMapper
):MovieDetailRepository {

    override suspend fun getMovieById(id: String): MovieModel {
        val response = apiService.getMovieById(movieId = id)
        Log.d("ZYZZ", mapper.mapResponseToEntity(response).toString())
        return mapper.mapResponseToEntity(response)
    }

}