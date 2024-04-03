package com.example.myapplication.data.remote

import retrofit2.http.POST
import retrofit2.http.Path

interface IServiceApi {
    @POST("many/sfw/{category}")
    suspend fun getWaifuData(@Path("category") category: String): WaifuResponse
}