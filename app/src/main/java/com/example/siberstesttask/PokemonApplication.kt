package com.example.siberstesttask

import android.app.Application
import com.example.siberstesttask.di.ApplicationModule
import com.example.siberstesttask.di.DaggerPokemonRepositoryComponent
import com.example.siberstesttask.di.PokemonRepositoryComponent

class PokemonApplication : Application() {
    lateinit var pokemonRepositoryComponent: PokemonRepositoryComponent
    override fun onCreate() {
        super.onCreate()
        pokemonRepositoryComponent = DaggerPokemonRepositoryComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}