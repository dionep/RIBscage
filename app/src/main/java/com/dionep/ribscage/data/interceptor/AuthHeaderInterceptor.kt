package com.dionep.ribscage.data.interceptor

import com.dionep.ribscage.data.Prefs
import okhttp3.Interceptor
import okhttp3.Response

class AuthHeaderInterceptor(
    private val prefs: Prefs
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = prefs.authToken
        return chain.proceed(
            with(chain.request().newBuilder()) {
                if (!token.isNullOrEmpty()) addHeader("auth-token", token)
                build()
            }
        )
    }

}