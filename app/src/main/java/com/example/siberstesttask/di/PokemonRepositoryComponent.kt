package com.example.siberstesttask.di

import com.example.siberstesttask.model.PokemonRepository
import com.example.siberstesttask.viewmodel.PokemonViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PokemonRepositoryModule::class])
interface PokemonRepositoryComponent {
    fun get(): PokemonRepository
    fun inject(viewModel: PokemonViewModel)
}