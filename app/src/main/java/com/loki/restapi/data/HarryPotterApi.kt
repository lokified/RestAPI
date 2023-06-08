package com.loki.restapi.data

import com.loki.restapi.data.response.Character
import retrofit2.http.GET
import retrofit2.http.Path

interface HarryPotterApi {

    @GET("characters")
    suspend fun getCharacters(): List<Character>

    @GET("character/{id}")
    suspend fun getCharacterDetail(
        @Path("id") id: String
    ): List<Character>
}