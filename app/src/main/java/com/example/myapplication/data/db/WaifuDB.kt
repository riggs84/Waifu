package com.example.myapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(version = 1, entities = [WaifuEntity::class])
@TypeConverters(DataConverter::class)
abstract class WaifuDB: RoomDatabase() {

    abstract fun getWaifuDB(): IWaifuDao
}