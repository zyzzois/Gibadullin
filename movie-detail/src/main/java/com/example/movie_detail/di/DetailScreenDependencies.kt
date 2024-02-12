package com.example.movie_detail.di

import com.example.movie_detail.domain.MovieDetailRepository
import kotlin.properties.Delegates

interface DetailScreenDependencies {
    val movieDetailRepository: MovieDetailRepository
}

interface DetailScreenDependenciesProvider {
    val dependencies: DetailScreenDependencies
    companion object : DetailScreenDependenciesProvider by DetailScreenDependenciesStore
}

object DetailScreenDependenciesStore: DetailScreenDependenciesProvider {
    override var dependencies: DetailScreenDependencies by Delegates.notNull()
}

