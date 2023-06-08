package com.loki.restapi.ui

import com.loki.restapi.data.response.Character

data class CharacterDetailState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val message: String = ""
)
