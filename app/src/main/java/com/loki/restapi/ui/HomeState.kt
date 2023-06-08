package com.loki.restapi.ui

import com.loki.restapi.data.response.Character

data class HomeState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val message: String = ""
)
