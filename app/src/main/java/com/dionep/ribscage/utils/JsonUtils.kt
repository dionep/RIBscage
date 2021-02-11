package com.dionep.ribscage.utils

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject

fun jsonOf(vararg pairs: Pair<String?, Any?>) =
    with(JSONObject()) {
        pairs.forEach {
            if (it.second != null) {
                putOpt(
                    it.first!!,
                    if (it.second is ArrayList<*>)
                        JSONArray(it.second as ArrayList<*>)
                    else it.second
                )
            }
        }
        this
    }


fun jsonRequestBodyOf(vararg pairs: Pair<String?, Any?>): RequestBody =
    jsonOf(*pairs).toString().toRequestBody("application/json".toMediaTypeOrNull())