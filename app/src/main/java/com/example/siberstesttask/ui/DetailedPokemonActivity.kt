package com.example.siberstesttask.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.siberstesttask.R
import com.example.siberstesttask.databinding.ActivityDetailedPokemonBinding


class DetailedPokemonActivity : AppCompatActivity() {
    companion object {
        const val ARGS_KEY = "args"
        fun startActivity(context: Context, argument: Parcelable) {
            context.startActivity(
                Intent(context, DetailedPokemonActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                    putExtra(ARGS_KEY, argument)
                }
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val binding: ActivityDetailedPokemonBinding =
            DataBindingUtil.setContentView(this,
                R.layout.activity_detailed_pokemon
            )
        binding.pokemon = intent.getParcelableExtra(ARGS_KEY)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}