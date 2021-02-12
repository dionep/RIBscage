package com.dionep.ribscage.di

import android.content.Context
import com.dionep.ribscage.App
import com.dionep.ribscage.AppActivity
import com.dionep.ribscage.data.Prefs
import com.dionep.ribscage.di.modules.NetworkModule
import com.dionep.ribscage.root.RootBuilder
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class
    ],
    dependencies = []
)
interface AppComponent: RootBuilder.ParentComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
    fun inject(activity: AppActivity)

}

@Module
object AppModule {

    @Provides
    @Singleton
    fun context(application: App): Context = application

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun prefs(
        context: Context,
        gson: Gson
    ): Prefs = Prefs(context, gson)

}