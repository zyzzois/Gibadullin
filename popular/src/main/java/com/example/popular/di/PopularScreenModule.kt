package com.example.popular.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.core_data.database.AppDatabase
import com.example.core_data.database.FavoritesMoviesDao
import com.example.core_data.di.ViewModelKey
import com.example.popular.data.network.MoviesApiService
import com.example.popular.data.repository.PopularMoviesRepositoryImpl
import com.example.popular.domain.PopularMoviesRepository
import com.example.popular.ui.PopularMoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module
interface PopularScreenModule {
    @Binds
    fun bindPopularMoviesRepository(
        popularMoviesRepositoryImpl: PopularMoviesRepositoryImpl
    ): PopularMoviesRepository

    @IntoMap
    @Binds
    @ViewModelKey(PopularMoviesViewModel::class)
    fun bindPopularMoviesViewModel(viewModel: PopularMoviesViewModel): ViewModel

    companion object {
        @Provides
        fun provideContext(application: Application): Context {
            return application.applicationContext
        }

        @Provides
        fun provideApiService(retrofit: Retrofit): MoviesApiService {
            return retrofit.create(MoviesApiService::class.java)
        }

        @Provides
        fun provideFavoriteMoviesDao(
            application: Application
        ): FavoritesMoviesDao {
            return AppDatabase.getInstance(application).moviesDao()
        }

    }
}