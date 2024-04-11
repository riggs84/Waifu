package com.example.myapplication.data.repository

import com.example.myapplication.data.db.IWaifuDao
import com.example.myapplication.data.db.WaifuEntity
import com.example.myapplication.domain.repository.IWaifuDataBaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WaifuDataBaseRepositoryImpl @Inject constructor(private val dataBase: IWaifuDao) :
    IWaifuDataBaseRepository {
    override fun getAll(): Flow<List<WaifuEntity>> {
        return dataBase.getAll()
    }
}