package com.example.movie_detail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_data.model.MovieModel
import com.example.movie_detail.domain.GetMovieByIdUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val getMovieByIdUseCase: GetMovieByIdUseCase
): ViewModel() {

    private val _movieInfo = MutableLiveData<MovieModel>()
    val movieInfo: LiveData<MovieModel>
        get() = _movieInfo

    fun getMovie(id: String) {
        viewModelScope.launch {
            _movieInfo.value = getMovieByIdUseCase(id)
        }
    }

}