package com.example.movie_detail.domain

import com.example.core_data.model.MovieModel
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(
    private val repository: MovieDetailRepository
) {
    suspend operator fun invoke(
        movieId: String
    ): MovieModel {
        return repository.getMovieById(movieId)
    }
}