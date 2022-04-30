package com.shinjh1253.googlebooksearch

import android.app.Application
import android.widget.Toast
import com.shinjh1253.googlebooksearch.core.util.GlideApp
import dagger.hilt.android.HiltAndroidApp
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import java.io.IOException
import java.net.SocketException

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        setupRxJavaErrorHandler()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        GlideApp.get(this).trimMemory(level)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        GlideApp.get(this).clearMemory()
    }

    private fun setupRxJavaErrorHandler() {
        RxJavaPlugins.setErrorHandler { e ->
            var error = e

            if (error is UndeliverableException) {
                error = e.cause
            }
            if (error is IOException || error is SocketException) {
                // fine, irrelevant network problem or API that throws on cancellation
                return@setErrorHandler
            }
            if (error is InterruptedException) {
                // fine, some blocking code was interrupted by a dispose call
                return@setErrorHandler
            }
            if (error is NullPointerException || error is IllegalArgumentException) {
                // that's likely a bug in the application
                Thread.currentThread().uncaughtExceptionHandler?.uncaughtException(Thread.currentThread(), error)
                return@setErrorHandler
            }
            if (error is IllegalStateException) {
                // that's a bug in RxJava or in a custom operator
                Thread.currentThread().uncaughtExceptionHandler?.uncaughtException(Thread.currentThread(), error)
                return@setErrorHandler
            }

            Toast.makeText(baseContext, error.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }
}