package com.example.myapplication.domain.repository

import com.example.myapplication.data.remote.WaifuResponse

interface IWaifuRepository {

    suspend fun getWaifuData(): WaifuResponse
}