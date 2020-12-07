package com.supercat.notes

import android.app.Application
import org.koin.core.context.startKoin

class TestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {  }
    }
}
