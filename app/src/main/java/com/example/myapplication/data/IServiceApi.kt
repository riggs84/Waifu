package com.example.myapplication.data

import retrofit2.http.POST
import retrofit2.http.Path

interface IServiceApi {
    @POST("sfw/{category}")
    suspend fun getWaifuData(@Path("category") category: String): List<Item>
}