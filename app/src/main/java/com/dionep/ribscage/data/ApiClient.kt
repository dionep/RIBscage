package com.dionep.ribscage.data

import com.dionep.ribscage.entity.User
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClient {

    @POST("register")
    fun registerAsync(
        @Body body: RequestBody
    ): Deferred<String>

    @GET("profile")
    fun getProfileAsync() : Deferred<User>

}