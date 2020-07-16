package com.example.siberstesttask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.siberstesttask.model.PokemonModel
import com.example.siberstesttask.model.PokemonRepository
import javax.inject.Inject

class PokemonViewModel : ViewModel() {
    @Inject
    lateinit var repository: PokemonRepository

    private val _pokemonData: LiveData<List<PokemonModel>> by lazy { repository.getAll() }

    val pokemonData: LiveData<List<PokemonModel>>
        get() = _pokemonData
    val isLoadingPage: Boolean
        get() = repository.isLoadingPage

    fun loadNextPage() {
        repository.loadNextPage()
    }

    fun reinitializeData() {
        repository.reinitializeData()
    }
}