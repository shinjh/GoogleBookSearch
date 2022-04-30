package com.shinjh1253.googlebooksearch.data.interceptor

import android.content.Context
import android.net.ConnectivityManager
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkConnectionInterceptor @Inject constructor(
    @ApplicationContext val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isConnected()) {
            throw NoConnectivityException()
        }

        return chain.proceed(chain.request().newBuilder().build())
    }

    private fun isConnected(): Boolean {
        return (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetwork != null
    }
}

class NoConnectivityException : IOException() {
    override fun getLocalizedMessage(): String {
        return "No Internet Connection"
    }
}