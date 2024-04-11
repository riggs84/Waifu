package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.db.WaifuDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideDataBase(ctx: Context): WaifuDB {
        return Room.databaseBuilder(ctx, WaifuDB::class.java, "waifu-database").build()
    }

    @Provides
    fun providesContext(): Context {
        return context
    }
}