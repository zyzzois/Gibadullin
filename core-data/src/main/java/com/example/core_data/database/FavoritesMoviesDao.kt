package com.example.core_data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoritesMoviesDao {

    @Query("SELECT * FROM movies")
    fun getFavoriteMoviesList(): List<MovieRoomEntity>

    @Insert
    suspend fun insertNewFavoriteMovie(movie: MovieRoomEntity)

    @Delete
    suspend fun deleteMovieFromFavorites(movie: MovieRoomEntity)

}