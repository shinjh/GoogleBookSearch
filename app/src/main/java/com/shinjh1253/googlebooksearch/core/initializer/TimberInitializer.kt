package com.shinjh1253.googlebooksearch.core.initializer

import android.content.Context
import androidx.startup.Initializer
import com.shinjh1253.googlebooksearch.BuildConfig
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}