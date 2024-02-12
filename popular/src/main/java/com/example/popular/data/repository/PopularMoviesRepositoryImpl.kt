package com.example.popular.data.repository

import com.example.core_data.database.FavoritesMoviesDao
import com.example.core_data.mapper.ModelRoomMapper
import com.example.core_data.model.MovieModel
import com.example.popular.data.mapper.ResponseEntityMapper
import com.example.popular.data.network.MoviesApiService
import com.example.popular.domain.PopularMoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PopularMoviesRepositoryImpl @Inject constructor(
    private val remoteDataMapper: ResponseEntityMapper,
    private val localDataMapper: ModelRoomMapper,
    private val apiService: MoviesApiService,
    private val favoriteMoviesDao: FavoritesMoviesDao,
): PopularMoviesRepository {

    override suspend fun getMoviesList(): List<MovieModel> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getTop100PopularMovies()
            val resultList = response.items.map {
                remoteDataMapper.mapDtoToEntity(it)
            }
            resultList
        }
    }

    override suspend fun addMovieToFavorites(movie: MovieModel) = withContext(Dispatchers.IO) {
        var shouldInsertMovie = true
        favoriteMoviesDao.getFavoriteMoviesList().forEach {
            if (it.title == movie.title) {
                shouldInsertMovie = false
            }
        }
        if (shouldInsertMovie) {
            favoriteMoviesDao.insertNewFavoriteMovie(
                movie = localDataMapper.mapModelToEntity(movie)
            )
        }
    }

    override suspend fun removeFromFavorites(movie: MovieModel) = withContext(Dispatchers.IO) {
        favoriteMoviesDao.deleteMovieFromFavorites(localDataMapper.mapModelToEntity(movie))
    }
}