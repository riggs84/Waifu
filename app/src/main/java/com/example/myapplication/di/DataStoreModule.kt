package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.data.repository.DataStoreRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataStoreModule(private val context: Context) {

    @Provides
    @Singleton
    fun providesDataStoreRepository(): DataStoreRepository {
        return DataStoreRepository(context)
    }
}