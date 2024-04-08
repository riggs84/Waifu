package com.example.myapplication.data.remote

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface IServiceApi {
    @Headers("Content-Type: application/json")
    @POST("many/sfw/{category}")
    suspend fun getWaifuData(
        @Path("category") category: String,
        @Body body: RequestBody
    ): Response<WaifuResponse>
}