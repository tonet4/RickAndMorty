package com.example.rickandmorty.di

import android.app.Application
import androidx.room.Room
import com.example.rickandmorty.data.data_source.favorites.local.FavoriteDataBase
import com.example.rickandmorty.data.data_source.favorites.local.room.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val FAVORITES_DB_NAME = "favorites_db"

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideFavoriteDatabase(app: Application): FavoriteDataBase {
        return Room.databaseBuilder(
            app,
            FavoriteDataBase::class.java,
            FAVORITES_DB_NAME
        ).build()
    }

    @Provides
    fun provideFavoriteDao(db: FavoriteDataBase): FavoriteDao = db.favoriteDao()

}