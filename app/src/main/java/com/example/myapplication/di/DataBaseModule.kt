package com.example.myapplication.di

import android.app.Application
import androidx.room.Room
import com.example.myapplication.data.db.IWaifuDao
import com.example.myapplication.data.db.WaifuDB
import com.example.myapplication.data.repository.WaifuDataBaseRepositoryImpl
import com.example.myapplication.domain.repository.IWaifuDataBaseRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataBaseBinds::class])
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(ctx: Application): IWaifuDao {
        val database = Room.databaseBuilder(ctx, WaifuDB::class.java, "waifu-database").build()
        return database.getWaifuDB()
    }
}

@Module
interface DataBaseBinds {
    @Binds
    fun binds(imp: WaifuDataBaseRepositoryImpl): IWaifuDataBaseRepository
}