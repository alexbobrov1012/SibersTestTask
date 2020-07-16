package com.example.siberstesttask.di

import android.app.Application
import androidx.room.Room
import com.example.siberstesttask.room.PokemonDao
import com.example.siberstesttask.room.PokemonDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationModule::class])
class PokemonDatabaseModule {
    @Singleton
    @Provides
    fun pokemonDatabase(application: Application): PokemonDatabase {
        return Room
            .databaseBuilder(application, PokemonDatabase::class.java, "pokemon_db")
            .build()
    }

    @Singleton
    @Provides
    fun pokemonDao(database: PokemonDatabase): PokemonDao {
        return database.pokemonDao()
    }
}