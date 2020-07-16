package com.example.siberstesttask.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.siberstesttask.model.PokemonModel

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pokemonList: List<PokemonModel>)

    @Query("DELETE FROM pokemon_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM pokemon_table")
    fun getAll(): LiveData<List<PokemonModel>>

    @Query("SELECT max(id) FROM pokemon_table")
    fun getLastPokemonId(): Int

    @Transaction
    suspend fun updateData(pokemonList: List<PokemonModel>) {
        deleteAll()
        insertAll(pokemonList)
    }
}