package com.shinjh1253.googlebooksearch.data.interceptor

import com.shinjh1253.googlebooksearch.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class DefaultHeaderInterceptor @Inject constructor(
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        // header
        getDefaultHeader().forEach { (key, value) ->
            builder.addHeader(key, value)
        }

        return chain.proceed(builder.build())
    }

    /**
     * Rest API 에서 사용하는 default headers
     */
    private fun getDefaultHeader(): Map<String, String> = mapOf(
        "Content-Type" to "application/json;charset=UTF-8",
        "Key" to BuildConfig.GOOGLE_API_KEY
    )
}