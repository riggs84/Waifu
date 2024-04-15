package com.example.myapplication.di

import com.example.myapplication.data.remote.IServiceApi
import com.example.myapplication.data.repository.WaifuRepositoryImpl
import com.example.myapplication.domain.repository.IWaifuRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.waifu.pics/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    fun getApiService(retrofit: Retrofit): IServiceApi {
        return retrofit.create(IServiceApi::class.java)
    }

    @Provides
    fun getWaifuRepository(api: IServiceApi): IWaifuRepository {
        return WaifuRepositoryImpl(api)
    }
}