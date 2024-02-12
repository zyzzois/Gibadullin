package com.example.favorites.di

import com.example.favorites.ui.FavoriteMoviesViewModel
import dagger.Component
import javax.inject.Scope

@FavoriteScreenScope
@Component(
    dependencies = [
        FavoriteScreenDependencies::class
    ]
)
interface FavoriteScreenComponent {
    fun getFavoriteMoviesViewModel(): FavoriteMoviesViewModel

    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: FavoriteScreenDependencies): Builder
        fun build(): FavoriteScreenComponent
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FavoriteScreenScope