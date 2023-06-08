package com.loki.restapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.loki.restapi.R
import com.loki.restapi.databinding.ActivityCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailBinding
    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")!!
        viewModel.getCharacterDetail(id)

        getCharacter()
    }

    private fun getCharacter() {

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->

                if (state.isLoading) {
                    binding.progress.visibility = View.VISIBLE
                }

                state.character?.let { character ->
                    binding.apply {
                        progress.visibility = View.GONE
                        tvCharacterName.text = character.name
                        tvCharacterGender.text = character.gender
                        tvCharacterHouse.text = character.house
                        tvCharacterDob.text = character.dateOfBirth
                    }
                }

                if (state.message.isNotBlank()) {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(this@CharacterDetailActivity, state.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}