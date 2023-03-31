package com.android.library.network.interceptor

import com.android.library.network.logging.Level
import com.android.library.network.logging.Printer
import formatJson
import okhttp3.*
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.internal.platform.Platform
import java.io.IOException
import java.util.concurrent.TimeUnit

internal class LoggingInterceptor : Interceptor {
    var isDebug: Boolean = false
    var type = Platform.INFO
    var requestTag: String = "HttpRequest"
    var responseTag: String = "HttpResponse"
    var level = Level.BASIC
    private val headers = Headers.Builder()
    var logger: Logger? = null

    interface Logger {
        fun log(level: Int, tag: String, msg: String)

        companion object {
            val DEFAULT: Logger = object : Logger {
                override fun log(level: Int, tag: String, msg: String) {
                    Platform.get().log(msg, level, null)
                }
            }
        }
    }

    private fun getHeaders(): Headers = headers.build()

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (getHeaders().size > 0) {
            val headers = request.headers
            val names = headers.names()
            val iterator = names.iterator()
            val requestBuilder = request.newBuilder()
            requestBuilder.headers(getHeaders())
            while (iterator.hasNext()) {
                val name = iterator.next()
                requestBuilder.addHeader(name, headers.get(name)!!)
            }
            request = requestBuilder.build()
        }
        if (!isDebug || level == Level.NONE)
            return chain.proceed(request)
        val requestBody = request.body
        var rContentType: MediaType? = null
        if (requestBody != null)
            rContentType = request.body!!.contentType()
        var rSubtype: String? = null
        if (rContentType != null)
            rSubtype = rContentType.subtype
        if (rSubtype != null && (rSubtype.contains("json")
                    || rSubtype.contains("xml")
                    || rSubtype.contains("plain")
                    || rSubtype.contains("html"))
        )
            Printer.printJsonRequest(this, request)
        else
            Printer.printFileRequest(this, request)
        val st = System.nanoTime()
        val response = chain.proceed(request)
        val segmentList = request.url.encodedPathSegments
        val chainMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - st)
        val header = response.headers.toString()
        val code = response.code
        val isSuccessful = response.isSuccessful
        val responseBody = response.body
        val contentType = responseBody!!.contentType()
        var subtype: String? = null
        val body: ResponseBody
        if (contentType != null)
            subtype = contentType.subtype
        if (subtype != null && (subtype.contains("json")
                    || subtype.contains("xml")
                    || subtype.contains("plain")
                    || subtype.contains("html"))
        ) {
            val bodyString = responseBody.string()
            val bodyJson = bodyString.formatJson()
            Printer.printJsonResponse(
                this,
                chainMs,
                isSuccessful,
                code,
                header,
                bodyJson,
                segmentList
            )
            body = bodyString.toResponseBody(contentType)
        } else {
            Printer.printFileResponse(this, chainMs, isSuccessful, code, header, segmentList)
            return response
        }
        return response.newBuilder().body(body).build()
    }
}