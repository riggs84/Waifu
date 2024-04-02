package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.IServiceApi
import com.example.myapplication.data.remote.WaifuResponse
import com.example.myapplication.domain.repository.IWaifuRepository
import com.example.myapplication.domain.util.Response
import java.lang.Exception
import javax.inject.Inject

class WaifuRepositoryImpl @Inject constructor(private val api: IServiceApi): IWaifuRepository {
    override suspend fun getWaifuData(): Response<WaifuResponse> {
       return try {
           Response.Success(
               data = api.getWaifuData("waifu")
           )
       } catch (e: Exception) {
           Response.Error(message = e.message.toString())
       }
    }
}