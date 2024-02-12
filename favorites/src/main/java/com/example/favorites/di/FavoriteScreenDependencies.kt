package com.example.favorites.di

import com.example.favorites.domain.FavoritesMoviesRepository
import kotlin.properties.Delegates

interface FavoriteScreenDependencies {
    val favoriteMoviesRepository: FavoritesMoviesRepository
}

interface FavoriteScreenDependenciesProvider {
    val dependencies: FavoriteScreenDependencies
    companion object : FavoriteScreenDependenciesProvider by FavoriteScreenDependenciesStore
}

object FavoriteScreenDependenciesStore: FavoriteScreenDependenciesProvider {
    override var dependencies: FavoriteScreenDependencies by Delegates.notNull()
}

