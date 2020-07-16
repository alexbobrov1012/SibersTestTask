package com.example.siberstesttask.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.siberstesttask.model.PokemonModel
import com.example.siberstesttask.R
import com.example.siberstesttask.databinding.LayoutListItemPokemonBinding
import com.example.siberstesttask.model.sortPokemonBy
import com.squareup.picasso.Picasso


class PokemonAdapter(private val listener: (PokemonModel) -> Unit) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    private var resultList: List<PokemonModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_list_item_pokemon,
                parent, false)
        return PokemonViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = resultList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener{ listener(item) }
    }

    fun setItems(list: List<PokemonModel>) {
        val previousLastIdx = resultList.size
        resultList = list
        notifyItemRangeInserted(previousLastIdx, list.size)
    }

    fun sortItems(attack: Boolean, defense: Boolean, hp: Boolean) {
        resultList = resultList.sortPokemonBy(attack, defense, hp)
        notifyDataSetChanged()
    }

    fun removeItems() {
        val size: Int = resultList.size
        resultList = emptyList()
        notifyItemRangeRemoved(0, size)
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding = LayoutListItemPokemonBinding.bind(itemView)

        fun bind(model: PokemonModel) {
            Picasso.with(binding.imageViewPicture.context)
                .load(model.pictureUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .fit()
                .into(binding.imageViewPicture)

            binding.groupTextViewName.setValueText(model.name)
            binding.groupTextViewAttack.setValueText(model.attack.toString())
            binding.groupTextViewDefense.setValueText(model.defense.toString())
            binding.groupTextViewHp.setValueText(model.hp.toString())
        }
    }
}