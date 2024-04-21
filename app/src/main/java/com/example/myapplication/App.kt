package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.AppComponent
import com.example.myapplication.di.DaggerAppComponent
import com.example.myapplication.di.DataBaseModule
import com.example.myapplication.di.DataStoreModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent =
            DaggerAppComponent.builder()
                .dataBaseModule(DataBaseModule(applicationContext))
                .dataStoreModule(DataStoreModule(applicationContext))
                .build()
    }
}