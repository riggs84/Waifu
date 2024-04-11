package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.db.IWaifuDao
import com.example.myapplication.data.db.WaifuDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideDataBase(ctx: Context): IWaifuDao {
        val database =  Room.databaseBuilder(ctx, WaifuDB::class.java, "waifu-database").build()
        return database.getWaifuDB()
    }

    @Provides
    fun providesContext(): Context {
        return context
    }
}