package com.example.suraapppractice

import android.app.Application
import com.example.suraapppractice.ki.sdRepositoriesModule
import com.example.suraapppractice.ki.sdSharedPref
import com.example.suraapppractice.ki.sdViewModelsModule
import com.example.suraapppractice.service.sdServicesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SDApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SDApplication)
            modules(
                listOf(
                    sdServicesModule,
                    sdRepositoriesModule,
                    sdSharedPref,
                    sdViewModelsModule
                )
            )
        }
    }
}