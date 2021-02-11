package com.dionep.ribscage.data

import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiClient {

    @POST("register")
    fun registerAsync(
        @Body body: RequestBody
    ): Deferred<String>

}