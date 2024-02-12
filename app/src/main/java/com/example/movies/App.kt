package com.example.movies

import android.app.Application
import com.example.favorites.di.FavoriteScreenDependenciesStore
import com.example.movie_detail.di.DetailScreenDependenciesStore
import com.example.movies.di.DaggerAppComponent
import com.example.popular.di.PopularScreenDependenciesStore

class App: Application() {
    private val appComponent by lazy {
         DaggerAppComponent.builder()
        .application(this)
        .build()
    }

    override fun onCreate() {
        super.onCreate()
        PopularScreenDependenciesStore.dependencies = appComponent
        DetailScreenDependenciesStore.dependencies = appComponent
        FavoriteScreenDependenciesStore.dependencies = appComponent
    }
}