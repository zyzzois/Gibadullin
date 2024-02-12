package com.example.popular.di

import com.example.popular.domain.PopularMoviesRepository
import kotlin.properties.Delegates

interface PopularScreenDependencies {
    val popularMoviesRepository: PopularMoviesRepository
}

interface PopularScreenDependenciesProvider {
    val dependencies: PopularScreenDependencies
    companion object : PopularScreenDependenciesProvider by PopularScreenDependenciesStore
}

object PopularScreenDependenciesStore: PopularScreenDependenciesProvider {
    override var dependencies: PopularScreenDependencies by Delegates.notNull()
}

