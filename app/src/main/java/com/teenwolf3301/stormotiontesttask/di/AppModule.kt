package com.teenwolf3301.stormotiontesttask.di

import com.teenwolf3301.stormotiontesttask.api.MockarooApi
import com.teenwolf3301.stormotiontesttask.utility.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMockarooApi(retrofit: Retrofit): MockarooApi =
        retrofit.create(MockarooApi::class.java)
}