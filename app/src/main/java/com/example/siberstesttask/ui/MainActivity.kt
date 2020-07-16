package com.example.siberstesttask.ui

import android.os.Bundle
import android.widget.CompoundButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siberstesttask.PokemonApplication
import com.example.siberstesttask.viewmodel.PokemonViewModel
import com.example.siberstesttask.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val viewModel: PokemonViewModel by viewModels()
    private val adapter =
        PokemonAdapter(listener = {
            DetailedPokemonActivity.startActivity(this, it)
        })

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (application as PokemonApplication).pokemonRepositoryComponent.inject(viewModel)

        setUpRecyclerView()
        setUpSearch()
        setUpReinitializeButton()

        viewModel.pokemonData.observe(this, Observer {
            if (it.isEmpty()) {
                viewModel.loadNextPage()
            }
            adapter.setItems(it)
        })
    }

    private fun setUpRecyclerView() {
        recyclerView = binding.pokemonListRecyclerView
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : PagingScrollListener(linearLayoutManager) {
            override fun loadMoreItems() {
                viewModel.loadNextPage()
            }

            override val isLoading: Boolean
                get() = viewModel.isLoadingPage
        })
    }

    private fun setUpSearch() {
        val listener = CompoundButton.OnCheckedChangeListener(function = { _, _ ->
            adapter.sortItems(
                binding.attackCheckBox.isChecked,
                binding.defenseCheckBox.isChecked,
                binding.hpCheckBox.isChecked
            )
            recyclerView.scrollToPosition(0)
        })
        binding.attackCheckBox.setOnCheckedChangeListener(listener)
        binding.defenseCheckBox.setOnCheckedChangeListener(listener)
        binding.hpCheckBox.setOnCheckedChangeListener(listener)
    }

    private fun setUpReinitializeButton() {
        binding.buttonReinitialize.setOnClickListener {
            adapter.removeItems()
            viewModel.reinitializeData()
        }
    }
}