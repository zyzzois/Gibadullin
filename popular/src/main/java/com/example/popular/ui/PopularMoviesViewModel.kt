package com.example.popular.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_data.model.MovieModel
import com.example.popular.domain.AddMovieToFavoritesUseCase
import com.example.popular.domain.GetPopularMovieListUseCase
import com.example.popular.domain.RemoveFromFavoritesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularMoviesViewModel @Inject constructor(
    private val getPopularMovieListUseCase: GetPopularMovieListUseCase,
    private val addMovieToFavoritesUseCase: AddMovieToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
): ViewModel() {

    private val _popularMovies = MutableLiveData<List<MovieModel>>()
    val popularMovies: LiveData<List<MovieModel>>
        get() = _popularMovies

    private val _searchedMovies = MutableLiveData<List<MovieModel>>()
    val searchedMovies: LiveData<List<MovieModel>>
        get() = _searchedMovies

    init {
        viewModelScope.launch {
            _popularMovies.value = getPopularMovieListUseCase()
        }
    }

    fun addMovieToFavoriteList(movie: MovieModel) {
        viewModelScope.launch {
            addMovieToFavoritesUseCase(movie)
        }
    }

    fun deleteMovieFromFavoriteList(movie: MovieModel) {
        viewModelScope.launch {
            removeFromFavoritesUseCase(movie)
        }
    }

    fun updateList(movie: MovieModel) {
        val oldList = _popularMovies.value
        val updatedList = _popularMovies.value?.map { item ->
            if (item.title == movie.title) {
                item.copy(isFavorite = !movie.isFavorite)
            } else {
                item
            }
        }
        _popularMovies.value = updatedList ?: oldList

    }

    fun searchMovies(query: String) {
        val result = mutableListOf<MovieModel>()
        _popularMovies.value?.forEach { movie ->
            if (movie.title.contains(query, ignoreCase = true)) {
                result.add(movie)
            }
        }
        _searchedMovies.value = result
    }

}