package com.example.movies.di

import android.app.Application
import com.example.core_data.di.CoreNetworkModule
import com.example.favorites.di.FavoriteScreenDependencies
import com.example.favorites.di.FavoriteScreenModule
import com.example.favorites.domain.FavoritesMoviesRepository
import com.example.movie_detail.di.DetailScreenDependencies
import com.example.movie_detail.di.DetailScreenModule
import com.example.movie_detail.domain.MovieDetailRepository
import com.example.popular.di.PopularScreenDependencies
import com.example.popular.di.PopularScreenModule
import com.example.popular.domain.PopularMoviesRepository
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [
        FavoriteScreenModule::class,
        DetailScreenModule::class,
        CoreNetworkModule::class,
        PopularScreenModule::class
    ]
)
interface AppComponent: PopularScreenDependencies, DetailScreenDependencies,
    FavoriteScreenDependencies {

    override val popularMoviesRepository: PopularMoviesRepository
    override val movieDetailRepository: MovieDetailRepository
    override val favoriteMoviesRepository: FavoritesMoviesRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build() : AppComponent
    }
}