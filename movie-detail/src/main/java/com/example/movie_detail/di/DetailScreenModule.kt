package com.example.movie_detail.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.core_data.di.ViewModelKey
import com.example.movie_detail.data.network.MovieDetailApiService
import com.example.movie_detail.data.repository.MovieDetailRepositoryImpl
import com.example.movie_detail.domain.MovieDetailRepository
import com.example.movie_detail.ui.MovieDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module
interface DetailScreenModule {
    @Binds
    fun bindMovieDetailRepository(
        movieDetailRepositoryImpl: MovieDetailRepositoryImpl
    ): MovieDetailRepository

    @IntoMap
    @Binds
    @ViewModelKey(MovieDetailViewModel::class)
    fun bindMovieDetailViewModel(viewModel: MovieDetailViewModel): ViewModel

    companion object {
        @Provides
        fun provideContext(application: Application): Context {
            return application.applicationContext
        }

        @Provides
        fun provideApiService(retrofit: Retrofit): MovieDetailApiService {
            return retrofit.create(MovieDetailApiService::class.java)
        }

    }
}