package com.loki.restapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.loki.restapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpUi()
        getCharacters()

    }

    private fun setUpUi() {
        adapter = CharacterAdapter()
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter
    }

    private fun getCharacters() {

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->

                if (state.isLoading) {
                    binding.progress.visibility = View.VISIBLE
                }

                if (state.characters.isNotEmpty()) {
                    binding.progress.visibility = View.GONE
                    adapter.setCharacterList(state.characters)
                }

                if (state.message.isNotBlank()) {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(this@MainActivity, state.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}