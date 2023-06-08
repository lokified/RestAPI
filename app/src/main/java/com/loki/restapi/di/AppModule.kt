package com.loki.restapi.di

import com.loki.restapi.data.HarryPotterApi
import com.loki.restapi.data.respository.GetCharactersRepository
import com.loki.restapi.data.respository.GetRepositoryImplRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://hp-api.onrender.com/api/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): HarryPotterApi {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HarryPotterApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofitApi(api: HarryPotterApi): GetCharactersRepository {
        return GetRepositoryImplRepository(api)
    }
}