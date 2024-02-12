package com.example.favorites.data.repository

import com.example.core_data.database.FavoritesMoviesDao
import com.example.core_data.model.MovieModel
import com.example.favorites.data.mapper.EntityModelMapper
import com.example.favorites.domain.FavoritesMoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteMoviesRepositoryImpl @Inject constructor(
    private val mapper: EntityModelMapper,
    private val favoriteMoviesDao: FavoritesMoviesDao,
): FavoritesMoviesRepository {

    override suspend fun getFavoritesMoviesList(): List<MovieModel> {
        return withContext(Dispatchers.IO) {
            favoriteMoviesDao.getFavoriteMoviesList().map {
                mapper.mapEntityToModel(it)
            }
        }
    }

}