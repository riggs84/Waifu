package com.example.myapplication.data.repository

import com.example.myapplication.data.db.WaifuDB
import com.example.myapplication.data.db.WaifuEntity
import com.example.myapplication.domain.repository.IWaifuDataBaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WaifuDataBaseRepositoryImpl @Inject constructor(private val dataBase: WaifuDB) :
    IWaifuDataBaseRepository {
    override fun getAll(): Flow<List<WaifuEntity>> {
        return dataBase.getWaifuDB().getAll()
    }
}