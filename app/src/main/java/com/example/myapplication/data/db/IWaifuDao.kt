package com.example.myapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface IWaifuDao {

    @Query("SELECT * from WAIFU")
    fun getAll(): Flow<List<WaifuEntity>>

    @Query("SELECT * from WAIFU where isFavorite = 1")
    fun getAllFavorites(): Flow<List<WaifuEntity>>

    @Insert(onConflict = OnConflictStrategy.NONE)
    fun insertList(items: List<WaifuEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateEntry(entry: WaifuEntity)
}