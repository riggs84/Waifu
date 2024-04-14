package com.example.myapplication.domain.repository

import com.example.myapplication.data.db.WaifuEntity
import kotlinx.coroutines.flow.Flow

interface IWaifuDataBaseRepository {

    fun getAll(): Flow<List<WaifuEntity>>

    fun insertList(list: List<WaifuEntity>)

    fun updateEntry(entry: WaifuEntity)
}