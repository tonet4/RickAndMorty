package com.example.rickandmorty.di

import com.example.rickandmorty.data.network_manager.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkManagerModule {

    @Provides
    @Singleton
    fun providesNetworkManager(): NetworkManager = NetworkManager()

}