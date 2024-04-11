package com.example.myapplication.data.db

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface IWaifuDao {

    @Query("SELECT * from WAIFU")
    fun getAll(): Flow<List<WaifuEntity>>
}