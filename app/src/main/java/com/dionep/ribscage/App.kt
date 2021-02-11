package com.dionep.ribscage

import android.app.Application
import com.dionep.ribscage.di.AppComponent

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponent.Starter.start(this)
        appComponent.inject(this)
    }


    companion object {
        lateinit var appComponent: AppComponent
    }
}