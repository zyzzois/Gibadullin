package com.example.popular.domain

import javax.inject.Inject

class GetPopularMovieListUseCase @Inject constructor(
    private val repository: PopularMoviesRepository
) {
    suspend operator fun invoke() = repository.getMoviesList()
}