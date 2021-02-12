package com.dionep.ribscage.di.modules

import com.dionep.ribscage.BuildConfig
import com.dionep.ribscage.Constants
import com.dionep.ribscage.data.ApiClient
import com.dionep.ribscage.data.Prefs
import com.dionep.ribscage.data.interceptor.AuthHeaderInterceptor
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun authHeaderInterceptor(prefs: Prefs) = AuthHeaderInterceptor(prefs)

    @Provides
    @Singleton
    fun okhttpClient(
        authHeaderInterceptor: AuthHeaderInterceptor
    ): OkHttpClient =
        with(OkHttpClient.Builder()) {
            addInterceptor(authHeaderInterceptor)
            connectTimeout(10, TimeUnit.SECONDS)
            writeTimeout(10, TimeUnit.SECONDS)
            readTimeout(10, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            if (BuildConfig.DEBUG) {
                addNetworkInterceptor(
                    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
                )
            }
            build()
        }

    @Provides
    @Singleton
    fun apiClient(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): ApiClient =
        with(Retrofit.Builder()) {
            addConverterFactory(GsonConverterFactory.create(gson))
            addCallAdapterFactory(CoroutineCallAdapterFactory())
            client(okHttpClient)
            baseUrl(Constants.Api.BASE_URL)
            build()
        }.create(ApiClient::class.java)

}
