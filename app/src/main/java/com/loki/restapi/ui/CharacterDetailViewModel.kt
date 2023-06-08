package com.loki.restapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loki.restapi.data.respository.GetCharactersRepository
import com.loki.restapi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val charactersRepository: GetCharactersRepository
): ViewModel() {


    private val _state = MutableStateFlow(CharacterDetailState())
    val state = _state.asStateFlow()

    fun getCharacterDetail(id: String) {

        viewModelScope.launch {
            charactersRepository.getCharacterDetail(id).collect { result ->

                when(result) {
                    is Resource.Loading -> {
                        _state.value = CharacterDetailState(
                            isLoading = true
                        )
                    }

                    is Resource.Success -> {
                        _state.value = CharacterDetailState(
                            character = result.data?.get(0)
                        )
                    }

                    is Resource.Error -> {
                        _state.value = CharacterDetailState(
                            message = result.message ?: "Something went wrong"
                        )
                    }
                }
            }
        }
    }
}