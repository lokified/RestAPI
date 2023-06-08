package com.loki.restapi.data.respository

import com.loki.restapi.data.response.Character
import com.loki.restapi.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetCharactersRepository {

    suspend fun getCharacters(): Flow<Resource<List<Character>>>

    suspend fun getCharacterDetail(id: String): Flow<Resource<List<Character>>>
}