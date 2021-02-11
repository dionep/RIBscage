package com.dionep.ribscage

import android.app.Application
import com.dionep.ribscage.di.AppComponent
import com.dionep.ribscage.di.DaggerAppComponent

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent =
            DaggerAppComponent
                .builder()
                .application(this)
                .build()
        appComponent.inject(this)
    }


    companion object {
        lateinit var appComponent: AppComponent
    }
}