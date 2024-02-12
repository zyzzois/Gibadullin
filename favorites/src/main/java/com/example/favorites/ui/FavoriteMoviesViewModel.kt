package com.example.favorites.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_data.model.MovieModel
import com.example.favorites.domain.GetFavoriteMoviesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteMoviesViewModel @Inject constructor(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
): ViewModel() {

    private val _favoritesMovies = MutableLiveData<List<MovieModel>>()
    val favoriteMovies: LiveData<List<MovieModel>>
        get() = _favoritesMovies

    private val _searchedQueryList = MutableLiveData<List<MovieModel>>()
    val searchedQueryList: LiveData<List<MovieModel>>
        get() = _searchedQueryList

    init {
        viewModelScope.launch {
            _favoritesMovies.value = getFavoriteMoviesUseCase()
        }
    }

    fun searchMovies(query: String) {
        val result = mutableListOf<MovieModel>()
        _favoritesMovies.value?.forEach { movie ->
            if (movie.title.contains(query, ignoreCase = true)) {
                result.add(movie)
            }
        }
        _searchedQueryList.value = result
    }

}