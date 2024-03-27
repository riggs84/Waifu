package com.example.myapplication.di

import com.example.myapplication.data.IServiceApi
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Component(modules = [ApiModule::class])
interface AppComponent {
}

@Module
object ApiModule {

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.waifu.pics/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getApiService(retrofit: Retrofit): IServiceApi {
        return retrofit.create(IServiceApi::class.java)
    }
}