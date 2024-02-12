package com.example.favorites.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.core_data.di.ViewModelKey
import com.example.favorites.data.repository.FavoriteMoviesRepositoryImpl
import com.example.favorites.domain.FavoritesMoviesRepository
import com.example.favorites.ui.FavoriteMoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface FavoriteScreenModule {
    @Binds
    fun bindFavoriteMoviesRepository(
        popularMoviesRepositoryImpl: FavoriteMoviesRepositoryImpl
    ): FavoritesMoviesRepository

    @IntoMap
    @Binds
    @ViewModelKey(FavoriteMoviesViewModel::class)
    fun bindFavoriteMoviesViewModel(viewModel: FavoriteMoviesViewModel): ViewModel

    companion object {
        @Provides
        fun provideContext(application: Application): Context {
            return application.applicationContext
        }
    }
}