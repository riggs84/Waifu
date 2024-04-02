package com.example.myapplication.di

import com.example.myapplication.data.remote.IServiceApi
import com.example.myapplication.data.repository.WaifuRepositoryImpl
import com.example.myapplication.domain.repository.IWaifuRepository
import com.example.myapplication.ui.home.HomeFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Component(modules = [ApiModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(fragment: HomeFragment)
}

@Module(includes = [BindsModule::class])
class ApiModule {

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

    @Provides
    @Singleton
    fun getWaifuRepository(api: IServiceApi): WaifuRepositoryImpl {
        return WaifuRepositoryImpl(api)
    }
}

@Module
interface BindsModule {

    @Binds
    fun bindWaifuRepository(waifuRepositoryImpl: WaifuRepositoryImpl): IWaifuRepository
}