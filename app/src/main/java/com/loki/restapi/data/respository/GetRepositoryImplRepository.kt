package com.loki.restapi.data.respository

import com.loki.restapi.data.HarryPotterApi
import com.loki.restapi.data.response.Character
import com.loki.restapi.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRepositoryImplRepository @Inject constructor(
    private val api: HarryPotterApi
): GetCharactersRepository  {

    override suspend fun getCharacters(): Flow<Resource<List<Character>>> = flow {

         try {
             emit(Resource.Loading(data = null))

             emit(Resource.Success(data = api.getCharacters()))
         } catch ( e: HttpException) {
             emit(Resource.Error(message = e.localizedMessage ?: "Something went wrong"))

         } catch (e: IOException) {
             emit(Resource.Error(message = "network not connected"))
         }
    }

    override suspend fun getCharacterDetail(id: String): Flow<Resource<List<Character>>> = flow {
        try {
            emit(Resource.Loading(data = null))

            emit(Resource.Success(data = api.getCharacterDetail(id)))
        } catch ( e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Something went wrong"))

        } catch (e: IOException) {
            emit(Resource.Error(message = "network not connected"))
        }
    }
}