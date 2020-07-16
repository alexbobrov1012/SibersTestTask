package com.example.siberstesttask.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.android.parcel.Parcelize
import kotlin.reflect.KProperty1

@Entity(tableName = "pokemon_table")
@TypeConverters(com.example.siberstesttask.room.TypeConverters::class)
@Parcelize
data class PokemonModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val baseExperience: Int,
    val height: Int,
    val weight: Int,
    val attack: Int,
    val defense: Int,
    val hp: Int,
    val types: List<String>,
    val forms: List<String>,
    val pictureUrl: String
) : Parcelable

fun <T: List<PokemonModel>> T.sortPokemonBy(attack: Boolean, defense: Boolean, hp: Boolean)
        : List<PokemonModel> {
    val listOfPredicates = ArrayList<KProperty1<PokemonModel, Int>>()
    if (attack) listOfPredicates.add(PokemonModel::attack)
    if (defense) listOfPredicates.add(PokemonModel::defense)
    if (hp) listOfPredicates.add(PokemonModel::hp)
    return if (listOfPredicates.isEmpty()) {
        sortedWith(compareBy(PokemonModel::id))
    } else {
        sortedWith(compareBy(*listOfPredicates.toTypedArray())).reversed()
    }
}

fun mapPokemon(pokemonJson: PokemonJsonModel): PokemonModel {
    var attack = 0
    var defense = 0
    var hp = 0
    val types = pokemonJson.types.map{ it.type.name }
    val forms = pokemonJson.forms.map{it.name}
    val pictureUrl = listOfNotNull(
        pokemonJson.sprites.front_default,
        pokemonJson.sprites.front_female,
        pokemonJson.sprites.front_shiny,
        pokemonJson.sprites.front_shiny_female,
        pokemonJson.sprites.back_default,
        pokemonJson.sprites.back_female,
        pokemonJson.sprites.back_shiny,
        pokemonJson.sprites.back_shiny_female,
        "no-value"
    ).first()

    pokemonJson.stats.forEach {
        val statValue = {it.base_stat}
        when(it.stat.name) {
            "attack" -> attack = statValue()
            "defense" -> defense = statValue()
            "hp" -> hp = statValue()
        }
    }
    return PokemonModel(
        pokemonJson.id,
        pokemonJson.name,
        pokemonJson.baseExperience,
        pokemonJson.height,
        pokemonJson.weight,
        attack,
        defense,
        hp,
        types,
        forms,
        pictureUrl
    )
}