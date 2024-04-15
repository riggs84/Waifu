package com.example.myapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [WaifuEntity::class])
abstract class WaifuDB: RoomDatabase() {

    abstract fun getWaifuDB(): IWaifuDao
}