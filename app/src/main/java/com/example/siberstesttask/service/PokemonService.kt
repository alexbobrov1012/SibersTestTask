package com.example.siberstesttask.service

import com.example.siberstesttask.model.ApiResourceList
import com.example.siberstesttask.model.PokemonJsonModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.*

interface PokemonService {
    @GET("pokemon")
    fun getPage(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ) : Call<ApiResourceList>

    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") id: Int) : Observable<PokemonJsonModel>
}