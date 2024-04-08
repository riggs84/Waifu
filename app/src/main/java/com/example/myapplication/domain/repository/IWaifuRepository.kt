package com.example.myapplication.domain.repository

import com.example.myapplication.data.remote.WaifuResponse
import retrofit2.Response

interface IWaifuRepository {

    suspend fun getWaifuData(): Response<WaifuResponse>
}