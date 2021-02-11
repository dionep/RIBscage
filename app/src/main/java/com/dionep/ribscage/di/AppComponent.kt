package com.dionep.ribscage.di

import com.dionep.ribscage.App
import com.dionep.ribscage.AppActivity
import com.dionep.ribscage.root.RootBuilder
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [],
    dependencies = []
)
interface AppComponent: RootBuilder.ParentComponent {

    class Starter {
        companion object {
            fun start(application: App) =
                DaggerAppComponent
                    .builder()
                    .application(application)
                    .build()
        }
    }

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
    fun inject(activity: AppActivity)

}