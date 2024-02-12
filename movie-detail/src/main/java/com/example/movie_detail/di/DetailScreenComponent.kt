package com.example.movie_detail.di

import com.example.movie_detail.ui.MovieDetailViewModel
import dagger.Component
import javax.inject.Scope

@DetailScreenScope
@Component(
    dependencies = [
        DetailScreenDependencies::class
    ]
)
interface DetailScreenComponent {
    fun getMovieDetailViewModel(): MovieDetailViewModel

    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: DetailScreenDependencies): Builder
        fun build(): DetailScreenComponent
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class DetailScreenScope