package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.IServiceApi
import com.example.myapplication.data.remote.WaifuResponse
import com.example.myapplication.domain.repository.IWaifuRepository
import javax.inject.Inject

class WaifuRepositoryImpl @Inject constructor(private val api: IServiceApi): IWaifuRepository {
    override suspend fun getWaifuData(): WaifuResponse = api.getWaifuData("waifu")
}