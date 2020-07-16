package com.example.siberstesttask.model

data class PokemonJsonModel(
    val id: Int,
    val name: String,
    val baseExperience: Int,
    val height: Int,
    val isDefault: Boolean,
    val order: Int,
    val weight: Int,
    val species: NamedApiResource,
    val forms: List<NamedApiResource>,
    val stats: List<PokemonStat>,
    val types: List<PokemonType>,
    val sprites: PokemonSprites
)

data class NamedApiResource(
    val name: String,
    val url: String
)

data class PokemonStat(
    val stat: NamedApiResource,
    val effort: Int,
    val base_stat: Int
)

data class PokemonType(
    val slot: Int,
    val type: NamedApiResource
)

data class PokemonSprites(
    val front_default: String?,
    val front_shiny: String?,
    val front_female: String?,
    val front_shiny_female: String?,
    val back_default: String?,
    val back_shiny: String?,
    val back_female: String?,
    val back_shiny_female: String?
)

data class ApiResourceList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ApiResource>
)

data class ApiResource(
    val url: String
)
