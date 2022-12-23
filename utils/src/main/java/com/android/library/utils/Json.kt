package com.android.library.utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

const val CONTENT_TYPE = "application/json;charset=utf-8"

val moshi: Moshi by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
}

inline fun <reified T> T.toJson(): String {
    return moshi.adapter(T::class.java).toJson(this)
}

inline fun <reified T> T.requestBody(): RequestBody {
    return toJson().toRequestBody(CONTENT_TYPE.toMediaType())
}