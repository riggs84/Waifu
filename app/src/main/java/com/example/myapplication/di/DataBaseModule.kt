package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.db.IWaifuDao
import com.example.myapplication.data.db.WaifuDB
import com.example.myapplication.data.repository.WaifuDataBaseRepositoryImpl
import com.example.myapplication.domain.repository.IWaifuDataBaseRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideDataBase(): IWaifuDao {
        val database = Room.databaseBuilder(context, WaifuDB::class.java, "waifu-database").build()
        return database.getWaifuDB()
    }

    @Provides
    fun providesDataBaseRepository(db: IWaifuDao): IWaifuDataBaseRepository {
        return WaifuDataBaseRepositoryImpl(db)
    }
}