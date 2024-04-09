package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.IServiceApi
import com.example.myapplication.data.remote.WaifuResponse
import com.example.myapplication.domain.repository.IWaifuRepository
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class WaifuRepositoryImpl @Inject constructor(private val api: IServiceApi) : IWaifuRepository {
    override suspend fun getWaifuData(): Response<WaifuResponse> {
        return api.getWaifuData(
            "waifu",
            RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), "{}")
        )
    }
}