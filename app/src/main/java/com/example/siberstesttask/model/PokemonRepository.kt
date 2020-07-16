package com.example.siberstesttask.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.siberstesttask.service.PokemonService
import com.example.siberstesttask.room.PokemonDao
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
import kotlin.collections.ArrayList
import kotlin.random.Random

class PokemonRepository(private val pokemonDao: PokemonDao, private val pokemonService: PokemonService) {
    companion object{
        const val PAGE_SIZE = 30
        const val TAG = "REPOSITORY"
    }
    var isLoadingPage = false
    private var currentPokemonIdx = 1

    init {
        GlobalScope.launch {
            pokemonDao.getLastPokemonId().let {
                if (it != 0) {
                    currentPokemonIdx = it
                }
            }
        }
    }

    fun loadNextPage() {
        isLoadingPage = true
        val pokemonRequests = ArrayList<Observable<PokemonJsonModel>>()
        for (i in currentPokemonIdx until currentPokemonIdx + PAGE_SIZE) {
            pokemonRequests.add(pokemonService.getPokemon(i))
        }
        currentPokemonIdx += PAGE_SIZE
        Observable.zip(pokemonRequests) {
            it.filterIsInstance<PokemonJsonModel>()
                .map(::mapPokemon)
                .apply(pokemonDao::insertAll)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    isLoadingPage = false
                },
                { t ->
                    isLoadingPage = false
                    Log.d(TAG, "fetchNextPage FAILED - ${t.message}")
                }
            )
    }

    fun reinitializeData() {
        pokemonService.getPage(0,0).enqueue(object : Callback, retrofit2.Callback<ApiResourceList> {
            override fun onFailure(call: Call<ApiResourceList>, t: Throwable) {
                Log.d(TAG, "Error fetching page - ${t.message}")
            }

            override fun onResponse(
                call: Call<ApiResourceList>,
                response: Response<ApiResourceList>
            ) {
                if (response.isSuccessful) {
                    response.body()?.count?.let { pokemonCount ->
                        GlobalScope.launch {
                            pokemonDao.deleteAll()
                            currentPokemonIdx = Random.nextInt(1, pokemonCount - PAGE_SIZE)
                        }
                    }
                }
            }

        })
    }

    fun getAll(): LiveData<List<PokemonModel>> {
        return pokemonDao.getAll()
    }
}