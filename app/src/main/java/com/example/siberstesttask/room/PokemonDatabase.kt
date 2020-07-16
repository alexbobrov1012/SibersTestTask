package com.example.siberstesttask.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.siberstesttask.model.PokemonModel

@Database(entities = [PokemonModel::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}