package com.example.popular.di

import com.example.popular.ui.PopularMoviesViewModel
import dagger.Component
import javax.inject.Scope

@PopularScreenScope
@Component(
    dependencies = [
        PopularScreenDependencies::class
    ]
)
interface PopularScreenComponent {
    fun getPopularMoviesViewModel(): PopularMoviesViewModel

    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: PopularScreenDependencies): Builder
        fun build(): PopularScreenComponent
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PopularScreenScope