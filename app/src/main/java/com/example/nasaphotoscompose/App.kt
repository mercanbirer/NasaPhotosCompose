package com.example.nasaphotoscompose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (false) {
            Timber.plant(Timber.DebugTree())
        }
    }
}