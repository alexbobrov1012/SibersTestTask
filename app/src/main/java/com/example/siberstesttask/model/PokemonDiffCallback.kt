package com.example.siberstesttask.model

import androidx.recyclerview.widget.DiffUtil

class PokemonDiffCallback(
    private val oldList: List<PokemonModel>,
    private val newList: List<PokemonModel>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}