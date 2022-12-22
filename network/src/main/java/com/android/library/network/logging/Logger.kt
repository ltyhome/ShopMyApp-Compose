package com.android.library.network.logging

import okhttp3.internal.platform.Platform

internal interface Logger {
    fun log(level: Int, tag: String, msg: String)

    companion object {
        val DEFAULT: Logger = object : Logger {
            override fun log(level: Int, tag: String, msg: String) {
                Platform.get().log(msg, level, null)
            }
        }
    }
}