package com.example.gameappinfo.di

import com.example.gameappinfo.core.utils.Constants
import com.example.gameappinfo.core.utils.Constants.BASE_URL
import com.example.gameappinfo.data.api.GameApi
import com.example.gameappinfo.data.repository.GameRepositoryImpl
import com.example.gameappinfo.domain.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideGameApi(retrofit: Retrofit) = retrofit.create(GameApi::class.java)

    @Provides
    @Singleton
    fun provideGameRepository(api: GameApi) : GameRepository {
        return GameRepositoryImpl(api)
    }
}