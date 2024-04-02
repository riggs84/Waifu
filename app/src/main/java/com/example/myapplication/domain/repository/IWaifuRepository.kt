package com.example.myapplication.domain.repository

import com.example.myapplication.data.remote.WaifuResponse
import com.example.myapplication.domain.util.Response

interface IWaifuRepository {

    suspend fun getWaifuData(): Response<WaifuResponse>
}