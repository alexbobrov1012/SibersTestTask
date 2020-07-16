package com.example.siberstesttask.di

import com.example.siberstesttask.room.PokemonDao
import com.example.siberstesttask.model.PokemonRepository
import com.example.siberstesttask.service.PokemonService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [PokemonDatabaseModule::class, PokemonServiceModule::class])
class PokemonRepositoryModule {
    @Singleton
    @Provides
    fun pokemonRepository(
        pokemonDao: PokemonDao,
        pokemonService: PokemonService
    ): PokemonRepository {
        return PokemonRepository(
            pokemonDao,
            pokemonService
        )
    }
}